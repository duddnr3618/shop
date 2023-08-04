package com.mysite.shop.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysite.shop.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {

	List<Item> findByItemName(String ItemName);
	
	List<Item> findByItemNameOrItemDetail(String itemName , String itemDetail);
	
	//JPQL 쿼리
	@Query("select i from Item i where i.itemDetail like %:itemDetail% " + "order by i.price desc" )
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
	//JPQL native쿼리 -> 실제 DBSQL쿼리
		@Query(value = "select * from Item i where i.item_detail like %:itemDetail% " + "order by i.price asc", 
					nativeQuery = true)
		List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
	
}
