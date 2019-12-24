
global float p
global char[] possible_colors={'r','g','b'}

class color_gain_tuple{
	public char color 
	public int gain
	public color_gain_tuple(char color,int gain){
		this.color=color
		this.gain=gain
	}
}

class Agent{
	char my_color
	Agent[] neighbours

	public Agent(){
		// set this.neighbours --Anylogic
		this.current_value=random.sample(possible_values)
	}

	public get_zerogain_colors(){
		char[] zerogain_colors={}
		int current_cost=compute_cost(this.my_color)
		for color in possible_colors
			if color!=this.my_color and (current_cost-compute_cost(color)==0)
				zerogain_colors.append(color)
		return zerogain_colors
	}

	public int compute_cost(char color){
		int cost=0
		for nb in this.neigbours
			if nb.color==color
				cost+=1
		return cost
	}



	public void calc_gain(){
		int current_cost=compute_cost(this.color)
		int best_gain=0
		int cost
		int gain
		char best_color
		for color in possible_colors
			cost=compute_cost(color)
			gain=current_cost-cost
			if gain>best_gain
				best_gain=gain
				best_color=color

		return color_gain_tuple(best_color,best_gain)
	}

	/*
	public void calc_neighbour_gains(){
		int[] neighbour_gains={}
		color_gain_tuple color_and_gain
		for ag in neigbours
			color_and_gain=ag.calc_gain()
			neighbour_gains.append(color_and_gain.gain)
		return neighbour_gains
	}
    */
	public void dsa_step(){
		best_color_and_gain=calc_gain()
		my_gain=best_color_and_gain.gain
		if best_color_and_gain.gain>0
			this.my_color=best_color_and_gain.color
		else
			zerogain_colors=get_zerogain_colors()
			if  (zerogain_colors not empty) and (random()<p)
				this.my_color=random.sample(zerogain_colors)


	}







}