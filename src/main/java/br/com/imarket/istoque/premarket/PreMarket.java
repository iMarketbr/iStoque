package br.com.imarket.istoque.premarket;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.imarket.istoque.configuration.json.LocalDateDeserializer;


public class PreMarket {

	private Long id;
	private String name;
	private String cnpj;
	private String email;
	private MarketAddress address;
	private boolean hasDelivery;
	private boolean approved;
	private String disapprovedText;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate changeDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MarketAddress getAddress() {
		return address;
	}

	public void setAddress(MarketAddress address) {
		this.address = address;
	}
	
	public boolean hasDelivery() {
		return hasDelivery;
	}
	
	public void setDelivery(boolean hasDelivery) {
		this.hasDelivery = hasDelivery;
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

	public LocalDate getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDate changeDate) {
		this.changeDate = changeDate;
	}
	
}
