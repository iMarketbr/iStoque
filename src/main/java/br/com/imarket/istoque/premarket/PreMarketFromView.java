package br.com.imarket.istoque.premarket;

public class PreMarketFromView {

	private Long id;
	private boolean approved;
	private String disapprovedText;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getDisapprovedText() {
		return disapprovedText;
	}
	public void setDisapprovedText(String disapprovedText) {
		this.disapprovedText = disapprovedText;
	}
	
}
