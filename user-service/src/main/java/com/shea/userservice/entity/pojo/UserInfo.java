package com.shea.userservice.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>
 * 
 * </p>
 *
 * @author Shea
 * @since 2025-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo implements Serializable,Iterable{

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 生日
     */
    private LocalDateTime birthDate;

    /**
     * 个人头像
     */
    private String avatar;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 信息是否完善 0：否 1：是

     */
    private Integer isCompleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @Override
    public Iterator iterator() {
        return new UserInfoIterator(this);
    }

    @Override
    public void forEach(Consumer action) {
        for(Object field : this){
            action.accept(field);
        }
    }

    private static class UserInfoIterator implements Iterator{

        private final Field[] fields;
        private final UserInfo userInfo;
        private int index = 0;
        private UserInfoIterator(UserInfo userInfo){
            Set<String> exclude = Set.of("id","userId","serialVersionUID");
            this.userInfo = userInfo;
            this.fields = Arrays.stream(userInfo.getClass().getDeclaredFields())
                    .filter(field -> !exclude.contains(field.getName())).toArray(Field[]::new);
            for (Field field : fields) {
                field.setAccessible(true);
            }
        }

        @Override
        public boolean hasNext() {
            return index < fields.length;
        }

        @Override
        public Object next() {
            if(!hasNext()){
                throw new NoSuchElementException("没有这个属性");
            }
            try {
                return fields[index++].get(userInfo);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
