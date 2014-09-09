package com.example.testsample;

public class CategoryRowItems {

	private String job_id = null;
	private String job_title = null;

	boolean selected = false;

	public CategoryRowItems(String job_id, String job_title, boolean selected) {

		this.job_id = job_id;
		this.job_title = job_title;
		this.selected = selected;

	}

	public String getjob_id() {
		return job_id;
	}

	public void setjob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getjob_title() {
		return job_title;
	}

	public void setjob_title(String job_title) {
		this.job_title = job_title;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
