public class Globals {
   public static float p=0.1;
   public static char[] possible_colors={'r','g','b'};
   ///
}


public class color_gain_tuple{
	public char color; 
	public int gain;
	public color_gain_tuple(char color,int gain){
		this.color=color;
		this.gain=gain;
	}
}

public class Agent{
	public char color;
	public Agent[] neighbours;
	
	public Agent(){
		// set this.neighbours --Anylogic
		this.color=random.sample(Globals.possible_colors);
	}

	public List<char> get_zerogain_colors(){
		List<char> zerogain_colors=new ArrayList<char>();
		int current_cost=this.compute_cost(this.color);
		for (char color:Globals.possible_colors){
			if color!=this.color and (current_cost-this.compute_cost(color)==0){
				zerogain_colors.add(color);
			}
		}
		return zerogain_colors
	}

	public int compute_cost(char color){
		int cost=0
		for (Agent nb:this.neigbours){
			if (nb.color==color) {
				cost+=1;
			}
		}
		return cost
	}


	public color_gain_tuple compute_gain(){
		int current_cost=this.compute_cost(this.color);
		int best_gain=0;
		int cost;
		int gain;
		char best_color;
		for (char color:Globals.possible_colors){
			cost=this.compute_cost(color);
			gain=current_cost-cost;
			if (gain>best_gain){
				best_gain=gain;
				best_color=color;
			}
		}

		return new color_gain_tuple(best_color,best_gain)
	}

	
	public void dsa_step(){
		best_color_and_gain=this.compute_gain();
		my_gain=best_color_and_gain.gain;
		if (best_color_and_gain.gain>0){
			this.color=best_color_and_gain.color;
		}
		else{
			zerogain_colors=this.get_zerogain_colors()
			if  ((zerogain_colors not empty) and (random()<Globals.p)){
				this.color=random.sample(zerogain_colors);
			}
		}

	}


/*
	public void calc_neighbour_gains(){
		int[] neighbour_gains={}
		color_gain_tuple color_and_gain
		for ag in neigbours
			color_and_gain=ag.compute_gain()
			neighbour_gains.append(color_and_gain.gain)
		return neighbour_gains
	}
    */




}