package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.StoreDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StoreDAO {
    //R 다 불러 오는 거 // user_id 추가
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude, store_image_path  from store order by store_id desc")
    public List<StoreDTO> readStore();

    // 선택한 게시물 가게정보, 메뉴 불러오기
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude, store_image_path, user_id  from store where store_id =#{storeId}")
    public StoreDTO readSelectStoreBy(int storeId);
    @Select("select store_name from store")
    public List<String> readStoreName();

    //R 유동적 불러오기 (wherlt re절 컬럼명 = 값)
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude from store where ${cn} = #{v}")
    public List<StoreDTO> readStoreBy(@Param("cn") String columnName, @Param("v") String value);

    //R 가게 이름으로 불러오기
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude from store where store_name like '%${search}%'")
    public List<StoreDTO> readStoreSearch(String search);
    // 수정할 가게 내용 불러오기
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude from store where store_id =#{storeId}")
    public StoreDTO ViewEditStore(int storeId);

    //U id 기준으로 가게 정보 수정 (가게이름, 주소, 전화번호, 가게상세설명, 위도, 경도, 사진)
    @Update("update store set store_name = #{storeName}, address= #{address}, phone_number = #{phoneNumber}, store_desc = #{storeDesc}, latitude = #{latitude}, longitude = #{longitude} where store_id = #{storeId}")
    public boolean updateStore(StoreDTO dto);

    //C 가게정보 생성 (가게이름, 주소, 전화번호, 가게상세설명, 위도, 경도, 사진) + 생성된 키 반환
    @Insert("insert into store (store_name, address, phone_number, store_desc, latitude, longitude, store_image_path, user_id) " +
            "values (#{storeName}, #{address}, #{phoneNumber}, #{storeDesc}, #{latitude}, #{longitude},  #{storeImagePath}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "storeId", keyColumn = "store_id")
    public int createStore(StoreDTO dto);
//    public boolean createStore(StoreDTO dto);

    //D id 기준으로 가게 정보 삭제
    @Delete("delete from store where store_id = ${storeId}")
    public boolean deleteStore(int storeId);

}

