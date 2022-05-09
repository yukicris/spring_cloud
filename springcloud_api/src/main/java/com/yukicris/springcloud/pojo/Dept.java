package com.yukicris.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.crypto.spec.PSource;
import java.io.Serializable;




@Data
@NoArgsConstructor
@Accessors(chain = true) // 链式写法
public class Dept implements Serializable {
    private Long deptno;

    private String dname;

    // 判断这个数据是存在哪个数据库的字段,微服务,一个服务对应一个数据库,同一个信息可能存在不同的数据库
    private String db_source;
}
