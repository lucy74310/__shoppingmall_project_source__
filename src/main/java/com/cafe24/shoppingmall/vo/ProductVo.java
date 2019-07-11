package com.cafe24.shoppingmall.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductVo {
	private Long no;
	@NotEmpty
	private String product_name;
	@NotEmpty
	private String product_price;
	@NotEmpty
	private String product_short_explain;
	private String product_detail;
	@NotEmpty
	private String displayed;
	@NotEmpty
	private String selling;
	@NotEmpty
	private String use_option;
	@NotEmpty
	private String use_stock;
	private int stock;
	@NotEmpty
	private String soldout_mark;
	private String reg_date;
	private int save_percentage;
	private int shipping_price;
	
	
	
	
	public ProductVo() {
	}
	
	
	
	public ProductVo(String product_name, String product_price, String product_short_explain, String product_detail,
			String displayed, String selling, String use_option, String use_stock, int stock, String soldout_mark,
			String reg_date, int save_percentage, int shipping_price) {
		super();
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_short_explain = product_short_explain;
		this.product_detail = product_detail;
		this.displayed = displayed;
		this.selling = selling;
		this.use_option = use_option;
		this.use_stock = use_stock;
		this.stock = stock;
		this.soldout_mark = soldout_mark;
		this.reg_date = reg_date;
		this.save_percentage = save_percentage;
		this.shipping_price = shipping_price;
	}



	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getProduct_short_explain() {
		return product_short_explain;
	}
	public void setProduct_short_explain(String product_short_explain) {
		this.product_short_explain = product_short_explain;
	}
	public String getProduct_detail() {
		return product_detail;
	}
	public void setProduct_detail(String product_detail) {
		this.product_detail = product_detail;
	}
	public String getDisplayed() {
		return displayed;
	}
	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}
	public String getSelling() {
		return selling;
	}
	public void setSelling(String selling) {
		this.selling = selling;
	}
	public String getUse_option() {
		return use_option;
	}
	public void setUse_option(String use_option) {
		this.use_option = use_option;
	}
	public String getUse_stock() {
		return use_stock;
	}
	public void setUse_stock(String use_stock) {
		this.use_stock = use_stock;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getSoldout_mark() {
		return soldout_mark;
	}
	public void setSoldout_mark(String soldout_mark) {
		this.soldout_mark = soldout_mark;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getSave_percentage() {
		return save_percentage;
	}
	public void setSave_percentage(int save_percentage) {
		this.save_percentage = save_percentage;
	}
	public int getShipping_price() {
		return shipping_price;
	}
	public void setShipping_price(int shipping_price) {
		this.shipping_price = shipping_price;
	}

	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", product_short_explain=" + product_short_explain + ", product_detail=" + product_detail
				+ ", displayed=" + displayed + ", selling=" + selling + ", use_option=" + use_option + ", use_stock="
				+ use_stock + ", stock=" + stock + ", soldout_mark=" + soldout_mark + ", reg_date=" + reg_date
				+ ", save_percentage=" + save_percentage + ", shipping_price=" + shipping_price + "]";
	}
	
	
	
	
	
	
	
}
