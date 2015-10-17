package com.shsx.freshmarket.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shsx.freshmarket.bean.Banner;
import com.shsx.freshmarket.bean.City;
import com.shsx.freshmarket.bean.FlashSale;
import com.shsx.freshmarket.bean.Floor;
import com.shsx.freshmarket.bean.Goods;
import com.shsx.freshmarket.bean.HomeBean;
import com.shsx.freshmarket.bean.ItemFloor;
import com.shsx.freshmarket.bean.Product;

public class JsonParase {
	public static List<City> GetCityList(String path){
		List<City> list = new ArrayList<City>();
		City city  = null;
		try {
			JSONObject jsonObject = new JSONObject(path);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for(int i = 0 ; i<jsonArray.length() ; i++){
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				city = new City();
				city.setCityName(jsonObject2.getString("CityName"));
				city.setCityId(jsonObject2.getString("CityId"));
				list.add(city);
			}
			
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * ������ҳ����
	 * @param json
	 * @return
	 */
	public static ArrayList<FlashSale> getFlashSaleList (String json){
		
		ArrayList<FlashSale> list = new ArrayList<FlashSale>();
		FlashSale sale = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject jsonObject2 = jsonObject.optJSONObject("data");
			JSONArray jsonArray = jsonObject2.optJSONArray("FlashSale");
			for(int i = 0 ; i<jsonArray.length() ; i++){
				JSONObject jsonObject3 = jsonArray.optJSONObject(i);
				sale = new FlashSale();
				sale.setBatch(jsonObject3.optInt("Batch"));
				sale.setStatus(jsonObject3.optInt("status"));
				sale.setTime(jsonObject3.optInt("time"));
				sale.setProductVariantPriceName(jsonObject3.optString("productVariantPriceName"));
				sale.setCreateTime(jsonObject3.optString("CreateTime"));
				sale.setEndTime(jsonObject3.optString("EndTime"));
				sale.setDiscount(jsonObject3.optString("Discount"));
				sale.setUnit(jsonObject3.optString("Unit"));
				sale.setStandard(jsonObject3.getBoolean("Standard"));
				sale.setPeriodMoney(jsonObject3.optDouble("PeriodMoney"));
				sale.setDefaultMoney(jsonObject3.optDouble("DefaultMoney"));
				sale.setProductId(jsonObject3.optInt("ProductId"));
				sale.setProductName(jsonObject3.optString("ProductName"));
				sale.setWeight(jsonObject3.optString("Weight"));
				sale.setPictureId(jsonObject3.optInt("PictureId"));
				sale.setServertime(jsonObject3.optString("servertime"));
				sale.setImage(jsonObject3.optString("image"));
				list.add(sale);
				sale = null;
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HomeBean getHomeBean (String json){
		HomeBean homeBean = new HomeBean();
		try {
			JSONObject jsonObject = new JSONObject(json);
			homeBean.setSuccess(jsonObject.optString("success"));
			List<Floor> data = new ArrayList<Floor>();
			JSONArray jsonArray = jsonObject.optJSONArray("data");
			for(int i=0 ; i<jsonArray.length() ; i++){
				JSONObject jsonObject2 = jsonArray.optJSONObject(i);
				Floor floor = new Floor();
				floor.setId(jsonObject2.optInt("Id"));
				floor.setTitle(jsonObject2.optString("Title"));
				floor.setType(jsonObject2.optInt("Type"));
				floor.setDescription(jsonObject2.optString("Description"));
				floor.setDisplayOrder(jsonObject2.optString("DisplayOrder"));
				List<ItemFloor> floorItem = new ArrayList<ItemFloor>();
				JSONArray jsonArray2 = jsonObject2.getJSONArray("FloorItem");
				for(int j=0 ; j<jsonArray2.length() ; j++){
					ItemFloor itemFloor = new ItemFloor();
					JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
					itemFloor.setPictureId(jsonObject3.optInt("PictureId"));
					itemFloor.setProductId(jsonObject3.optInt("productId"));
					itemFloor.setProductName(jsonObject3.optString("ProductName"));
					itemFloor.setWeight(jsonObject3.optString("Weight"));
					itemFloor.setPRICE(jsonObject3.optDouble("PRICE"));
					itemFloor.setOLDPRICE(jsonObject3.optDouble("OLDPRICE"));
					itemFloor.setPRICENAME(jsonObject3.optString("PRICENAME"));
					itemFloor.setEndTime(jsonObject3.optString("EndTime"));
					itemFloor.setImage(jsonObject3.optString("image"));
					itemFloor.setFloorMasterId(jsonObject3.optInt("FloorMasterId"));
					itemFloor.setUrl(jsonObject3.optString("Url"));
					itemFloor.setLinkType(jsonObject3.optInt("LinkType"));
					itemFloor.setId(jsonObject3.optInt("Id"));
					itemFloor.setLinkContent(jsonObject3.optString("LinkContent"));
					itemFloor.setLinkContentName(jsonObject3.optString("LinkContentName"));
					itemFloor.setProductVariantId(jsonObject3.optInt("ProductVariantId"));
					floorItem.add(itemFloor);
				}
				floor.setFloorItem(floorItem);
				data.add(floor);
			}
			homeBean.setData(data );
			return homeBean;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Goods getGoods(String json){
		Goods goods = new Goods();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject optJSONObject = jsonObject.optJSONObject("data");
			JSONArray jsonArray = optJSONObject.optJSONArray("banner");
			List<Banner> banners = new ArrayList<Banner>();
			for(int i = 0 ; i<jsonArray.length() ; i++){
				JSONObject jsonObject2 = jsonArray.optJSONObject(i);
				Banner banner = new Banner();
				banner.setImage(jsonObject2.optString("image"));
				banners.add(banner);
			}
			goods.setBanner(banners);
			JSONObject object = optJSONObject.optJSONObject("product");
			Product product = new Product();
			product.setBuy_end_time(object.optString("buy_end_time"));
			product.setNumber(object.optString("number"));
			product.setName(object.optString("name"));
			product.setPlace(object.optString("place"));
			product.setBrand(object.optString("brand"));
			product.setStore(object.optString("store"));
			product.setPublished(object.optString("published"));
			product.setRelease_time(object.optString("release_time"));
			product.setSell(object.optString("sell"));
			product.setPresent(object.optString("present"));
			product.setStock_quantity(object.optString("stock_quantity"));
			product.setOut_stock(object.optString("out_stock"));
			product.setDisplayStockAvailability(object.optString("DisplayStockAvailability"));
			product.setReminder(object.optString("reminder"));
			product.setSx_price_name(object.optString("sx_price_name"));
			product.setSx_price(object.optString("sx_pricesx_price"));
			product.setSp_weight(object.optString("sp_weight"));
			product.setOld_price_name(object.optString("old_price_name"));
			product.setOld_price(object.optString("old_price"));
			product.setOld_price_weigh(object.optString("old_price_weigh"));
			product.setRate_price(object.optString("rate_price"));
			product.setRate_weight(object.optString("rate_weight"));
			product.setStandard(object.optString("standard"));
			product.setInfo(object.optString("info"));
			product.setLast_time_info(object.optString("last_time_info"));
			product.setEnd_last_time(object.optString("end_last_time"));
			product.setMax_end_time(object.optString("max_end_time"));
			product.setId(object.optString("id"));
			product.setSpecialPriceName(object.optString("specialPriceName"));
			product.setSpecialPriceTips(object.optString("specialPriceTips"));
			product.setIsHighlightByRed(object.optString("isHighlightByRed"));
			product.setCollect(object.optString("collect"));
			product.setTotalPoint(object.optString("totalPoint"));
			product.setExsitReflex(object.optString("exsitReflex"));
			goods.setProduct(product);
			
			return goods;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	
}
