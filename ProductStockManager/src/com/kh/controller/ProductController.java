package com.kh.controller;

import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIo;
import com.kh.view.ProductMenu;


public class ProductController {

	public void selectList() {
		
	 ArrayList<Product>	list =new ProductService().selectList();
	 
	 if(list.isEmpty()) {
		 new ProductMenu().displayNoData("조회 결과가 없습니다.");
	 }else {
		 new ProductMenu().displayProductList(list);
	 }
		
		
	}

	public void insertProduct(String productId, String pName, String price, String description, String stock) {
		
		Product p = new Product(productId, pName, Integer.parseInt(price), description , Integer.parseInt(stock)); 
		
		int result = new ProductService().insertProduct(p);
		
		if(result>0) {
			new ProductMenu().displaySuccess("성공적으로 상품 추가 되었습니다.");
		}else {
			new ProductMenu().displayFail("상품 추가가 실패하였습니다.");
		}
		
		
	}

	public void updateProduct(String productId, String pName, String price, String description, String stock) {
		Product p = new Product();
		
		p.setProductId(productId);
		p.setpName(pName);
		p.setPrice(Integer.parseInt(price));
		p.setDescription(description);
		p.setStock(Integer.parseInt(stock));
		
		int result = new ProductService().updateProduct(p);
		
		if(result>0) {
			new ProductMenu().displaySuccess("성공적으로 상품수정 되었습니다.");
		}else {
			new ProductMenu().displayFail("상품 정보 수정이 실패하였습니다.");
		}
		
	}

	public void deleteProduct(String productId) {
		int result = new ProductService().deleteProduct(productId);
		
		if(result>0) {
			new ProductMenu().displaySuccess("성공적으로 상품삭제 되었습니다.");
		}else {
			new ProductMenu().displayFail("상품 삭제가 실패하였습니다.");
		}
	}


	public void selectByProductName(String keyword) {
		ArrayList<Product>	list =new ProductService().selectByProductName(keyword);
		 
		 if(list.isEmpty()) {
			 new ProductMenu().displayNoData("검색 결과가 없습니다.");
		 }else {
			 new ProductMenu().displayProductList(list);
		 }
			
		
	}

	public void selectIoList() {
		ArrayList<ProductIo> list =new ProductService().selectIoList();
		 
		 if(list.isEmpty()) {
			 new ProductMenu().displayNoData("조회 결과가 없습니다.");
		 }else {
			 new ProductMenu().displayProductIoList(list);
		 }
		
	}

	public void selectICList() {
		ArrayList<ProductIo> list =new ProductService().selectICList();
		 
		 if(list.isEmpty()) {
			 new ProductMenu().displayNoData("조회 결과가 없습니다.");
		 }else {
			 new ProductMenu().displayProductICList(list);
		 }	
	}

	public void selectOGList() {
		ArrayList<ProductIo> list =new ProductService().selectOGList();
		 
		 if(list.isEmpty()) {
			 new ProductMenu().displayNoData("조회 결과가 없습니다.");
		 }else {
			 new ProductMenu().displayProductOGList(list);
		 }
		
	}

	public void updateIC(String productId, int amount) {
		int result = new ProductService().updateIC(productId, amount);	
		
		if(result>0) {
			new ProductMenu().displaySuccess("성공적으로 입고 되었습니다.");
		}else {
			new ProductMenu().displayFail("상품 입고가 실패하였습니다.");
		}
		
	}

	public void updateOG(String productId, int amount) {
		int result = new ProductService().updateOG(productId, amount);	
		
		if(result>0) {
			new ProductMenu().displaySuccess("성공적으로 출고 되었습니다.");
		}else {
			new ProductMenu().displayFail("상품 출고가 실패하였습니다.");
		}		
	}

}
