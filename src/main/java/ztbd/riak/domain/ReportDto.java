package ztbd.riak.domain;

import java.util.Map;

public class ReportDto {

	private Map<String, Integer> groupByCategory;

	private Map<String, Integer> groupBySubCategory;

	private Map<String, Integer> groupByYears;

	private Map<String, Integer> groupByRatings;

	public ReportDto() {
	}

	public Map<String, Integer> getGroupByCategory() {
		return groupByCategory;
	}

	public void setGroupByCategory(Map<String, Integer> groupByCategory) {
		this.groupByCategory = groupByCategory;
	}

	public Map<String, Integer> getGroupBySubCategory() {
		return groupBySubCategory;
	}

	public void setGroupBySubCategory(Map<String, Integer> groupBySubCategory) {
		this.groupBySubCategory = groupBySubCategory;
	}

	public Map<String, Integer> getGroupByYears() {
		return groupByYears;
	}

	public void setGroupByYears(Map<String, Integer> groupByYears) {
		this.groupByYears = groupByYears;
	}

	public Map<String, Integer> getGroupByRatings() {
		return groupByRatings;
	}

	public void setGroupByRatings(Map<String, Integer> groupByRatings) {
		this.groupByRatings = groupByRatings;
	}

}
