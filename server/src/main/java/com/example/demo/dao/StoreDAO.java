package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.StoreDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StoreDAO {
    //R 다 불러 오는 거 // user_id 추가??
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude  from store")
    public List<StoreDTO> readStore();

    // 메뉴랑 가게 불러오기
    @Select("select s.store_id, s.store_name, s.address, s.phone_number, s.store_desc, s.store_image_path, m.menu_id, m.menu_name, m.menu_image_path\n" +
            "from store s inner join menu m on s.store_id = m.store_id")
    public  Map<String, Object> readStoreAndMenu();

    // 선택한 게시물 가게정보, 메뉴 불러오기
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude, store_image_path  from store where store_id =#{storeId}")
    public StoreDTO readSelectStoreBy(int storeId);

    //R 유동적 불러오기 (wherlt re절 컬럼명 = 값)
    @Select("select store_id, store_name, address, phone_number, store_desc, latitude, longitude from store where ${cn} = #{v}")
    public List<StoreDTO> readStoreBy(@Param("cn") String columnName, @Param("v") String value);

    //U id 기준으로 가게 정보 수정 (가게이름, 주소, 전화번호, 가게상세설명, 위도, 경도, 사진)
    @Update("update store set store_name = #{storeName}, address= #{address}, phone_number = #{phoneNumber}, store_desc = #{storeDesc}, latitude = #{latitude}, longitude = #{longitude} where store_id = #{storeId}")
    public boolean updateStore(StoreDTO dto);

    //C 가게정보 생성 (가게이름, 주소, 전화번호, 가게상세설명, 위도, 경도, 사진) + 생성된 키 반환
    @Insert("insert into store (store_name, address, phone_number, store_desc, latitude, longitude, store_image_path) " +
            "values (#{storeName}, #{address}, #{phoneNumber}, #{storeDesc}, #{latitude}, #{longitude},  #{storeImagePath})")
    @Options(useGeneratedKeys = true, keyProperty = "storeId", keyColumn = "store_id")
    public int createStore(StoreDTO dto);
//    public boolean createStore(StoreDTO dto);

    //D id 기준으로 가게 정보 삭제
    @Delete("delete from store where store_id = ${storeId}")
    public boolean deleteStore(int storeId);

}

