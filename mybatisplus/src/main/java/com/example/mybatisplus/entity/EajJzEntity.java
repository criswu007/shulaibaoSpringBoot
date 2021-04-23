package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wudb
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("EAJ_JZ_")
@FieldNameConstants
public class EajJzEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AHDM")
    private String ahdm;

    @TableField("XH")
    private String xh;

    @TableField("STLB")
    private String stlb;

    @TableField("LB")
    private String lb;

    @TableField("STBH")
    private Integer stbh;

    @TableField("MC")
    private String mc;

    @TableField("BZ")
    private String bz;

    @TableField("ZZSJ")
    private Date zzsj;

    @TableField("ZZR")
    private String zzr;

    @TableField("ZJXGR")
    private String zjxgr;

    @TableField("TJR")
    private String tjr;

    @TableField("TJRQ")
    private Date tjrq;

    @TableField("ZFJBS")
    private String zfjbs;

    @TableField("JXPXH")
    private Integer jxpxh;

    @TableField("JDZT")
    private String jdzt;

    @TableField("YSBH")
    private Integer ysbh;

    @TableField("WJMC")
    private String wjmc;

    @TableField("WJGS")
    private String wjgs;

    @TableField("WJDX")
    private Integer wjdx;

    @TableField("NR")
    private byte[] nr;

    @TableField("YSBZ")
    private String ysbz;

    @TableField("LCZT")
    private String lczt;

    @TableField("LCH")
    private String lch;

    @TableField("SLH")
    private String slh;

    @TableField("LASTUPDATE")
    private Date lastupdate;

    @TableField("SENDID")
    private Integer sendid;

    @TableField("QZID")
    private Integer qzid;

    @TableField("YZZT")
    private String yzzt;

    @TableField("FILENAME")
    private String filename;

    @TableField("FTPSERVER")
    private String ftpserver;

    @TableField("WJYS")
    private Integer wjys;

    @TableField("JMXH")
    private Integer jmxh;

    @TableField("TQYY")
    private String tqyy;

    @TableField("SFFBCPWS")
    private String sffbcpws;

    @TableField("WJZS")
    private BigDecimal wjzs;

    @TableField("WJFS")
    private Integer wjfs;

    @TableField("WJJS")
    private Integer wjjs;

    @TableField("WJCS")
    private Integer wjcs;

    @TableField("SFDZSD")
    private String sfdzsd;

    @TableField("TEDTIME")
    private Integer tedtime;

    @TableField("ETIME")
    private Integer etime;

    @TableField("GZWCDD")
    private String gzwcdd;

    @TableField("HDID")
    private Integer hdid;

    @TableField("YWXH")
    private String ywxh;

    @TableField("SFZYXJ")
    private String sfzyxj;

    @TableField("YJFYJ")
    private String yjfyj;

    @TableField("SYDSR")
    private String sydsr;

    @TableField("HASHCODE")
    private String hashcode;

    @TableField("SFDG")
    private String sfdg;

    @TableField("LCTQR")
    private String lctqr;

    @TableField("FJM")
    private String fjm;

    @TableField("TSTAMP")
    private Date tstamp;

    @TableField("YYXHS")
    private String yyxhs;

    @TableField("GKSJ")
    private String gksj;

    @TableField("SFGK")
    private String sfgk;

    @TableField("SQCJ")
    private String sqcj;

    @TableField("CLLY")
    private String clly;

    @TableField("TJXS")
    private String tjxs;

    @TableField("CLXS")
    private String clxs;

    @TableField("FYDM")
    private String fydm;

    @TableField("DSRXH")
    private String dsrxh;

    @TableField("MLLB")
    private String mllb;

    @TableField("SPXTID")
    private String spxtid;

    @TableField("CLLYID")
    private String cllyid;

    @TableField("ZHSJ")
    private Integer zhsj;

    @TableField("DAXH")
    private String daxh;

    @TableField("GDMLXH")
    private Integer gdmlxh;

    @TableField("SFXSQ")
    private String sfxsq;

    @TableField("SSDR")
    private String ssdr;

    @TableField("SFXSD")
    private String sfxsd;

    @TableField("ROWUUID")
    private String rowuuid;

    @TableField("CCFS")
    private String ccfs;

    @TableField("OSSNAME")
    private String ossname;

    @TableField("OSSPATH")
    private String osspath;

    @TableField("OSSPATHID")
    private String osspathid;

    @TableField("STMC")
    private String stmc;

    @TableField("SDZT")
    private String sdzt;

    @TableField("SFYY")
    private String sfyy;

    @TableField("GDMLPXH")
    private Integer gdmlpxh;

    @TableField("SFWG")
    private String sfwg;

    @TableField("GKFW")
    private String gkfw;

    @TableField("SFJC")
    private String sfjc;

    @TableField("SFXG")
    private String sfxg;

    @TableField("GKZT")
    private String gkzt;

    @TableField("BAGK")
    private String bagk;

    @TableField("BGKYY")
    private String bgkyy;

    @TableField("SFGD")
    private String sfgd;

    @TableField("SDJD")
    private String sdjd;

    @TableField("ZH15")
    private String zh15;

    @TableField("SFZHPDF")
    private String sfzhpdf;

    @TableField("CZZZCL")
    private String czzzcl;

    @TableField("BSDYY")
    private String bsdyy;

    @TableField("YYDM")
    private String yydm;

    @TableField("YWLX")
    private String ywlx;

    @TableField("CLTJR")
    private String cltjr;

    @TableField("JNMC")
    private String jnmc;

    @TableField("SFBZMC")
    private String sfbzmc;

    @TableField("CLLX")
    private String cllx;

    @TableField("BGKSQ")
    private String bgksq;

    @TableField("SYNCJBS")
    private String syncjbs;

    @TableField("EJLB")
    private String ejlb;

    @TableField("QFR")
    private String qfr;

    @TableField("QFRQ")
    private String qfrq;

    @TableField("SFDGT")
    private String sfdgt;

    @TableField("SFYXWS")
    private String sfyxws;

    @TableField("STM")
    private String stm;

    @TableField("YWBS")
    private String ywbs;


}
