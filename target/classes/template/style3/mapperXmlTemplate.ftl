<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${mapperPackage}.${entity}Mapper">
<#assign size = table.fields?size-1>
    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="${entityPackage}.${entity}Entity" id="BaseResultMap">
        <#list table.fields as field>
        <result property="${field.propertyName}" column="${field.name}"/>
        </#list>
    </resultMap>

    <sql id="selectColumns">
        select
         <#list table.fields as field>
        ${field.name}<#if field_index!=size>,</#if>
         </#list>
        from ${table.name} a
    </sql>

    <select id="get" resultMap="BaseResultMap">
        <include refid="selectColumns"/>
        WHERE a.id = ${'#'}{id}
    </select>

    <!-- 查询有效数据列表，可分页查询 -->
    <select id="findList" resultMap="BaseResultMap">
        <include refid="selectColumns"/>
        order by id desc
    </select>

    <insert id="insert" parameterType="${entityPackage}.${entity}Entity" useGeneratedKeys="true" keyProperty="id">
        insert into ${table.name}
        (
        <#list table.fields as field>
         `${field.name}`<#if field_index!=size>,</#if>
        </#list>
        )
        values
        (
        <#list table.fields as field>
        ${'#'}{${field.propertyName}}<#if field_index!=size>,</#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="${entityPackage}.${entity}Entity">
        update ${table.name}
        <set>
            <#list table.fields as field>
                <#if field.name !='id'>
         <if test="${field.propertyName} != null">`${field.name}` = ${'#'}{${field.propertyName}}<#if field_index!=size>,</#if></if>
                </#if>
            </#list>
        </set>
        where id = ${'#'}{id}
    </update>

    <update id="deleteById">
        update ${table.name} set del_flag='1'  where id = ${'#'}{id}
    </update>

</mapper>