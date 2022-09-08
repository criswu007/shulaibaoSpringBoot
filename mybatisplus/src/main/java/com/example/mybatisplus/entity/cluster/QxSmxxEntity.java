package com.example.mybatisplus.entity.cluster;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("QX_SMXX")
public class QxSmxxEntity implements Serializable {
    @TableId("FWLSH")
    private String fwlsh;

    @TableField("FWBH")
    private String fwbh;
}
