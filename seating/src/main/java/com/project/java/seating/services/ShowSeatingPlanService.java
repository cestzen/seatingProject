package com.project.java.seating.services;

import java.util.List;

import com.project.java.seating.bdd.PlanBdd;
import com.project.java.seating.model.Plan;

public class ShowSeatingPlanService {
	private PlanBdd planBdd;
	
	public ShowSeatingPlanService() {
	}
	
	

	public void setPlanBdd(PlanBdd planBdd) {
		this.planBdd = planBdd;
	}



	public String[] getFloorPlans(String nomBatiment){
		List<Plan> planList = planBdd.get(nomBatiment);
		String[] plans = new String[planList.size()];
		
		for(int i = 0; i < planList.size() ; i++)
			plans[i] = planList.get(i).getNom();
		
		return plans;
		
	}
	
	public void getFloorPlans(int floor){
		
	}

	public static void main(String[] args) {
		ShowSeatingPlanService se = new ShowSeatingPlanService();
		System.out.println(se.getFloorPlans("Hulusi")[0]);
	}
	
}
