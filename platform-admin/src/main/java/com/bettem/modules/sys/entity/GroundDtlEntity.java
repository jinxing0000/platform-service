package com.bettem.modules.sys.entity;

import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 耕地地力补贴明细
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-04-23 19:47:05
 */
@Document(collection = "GROUND_DTL_2018")
public class GroundDtlEntity extends BaseEntity {
	/**
	 * 户ID
	 */
	@Field("FAMILY_ID")
	private String familyId;
	/**
	 * 户编号
	 */
	private String familyCode;
	/**
	 * 农民姓名
	 */
	@Field("FULL_NAME")
	private String fullName;
	/**
	 * 农民身份证号
	 */
	@Field("CARD_NUMBER")
	private String cardNumber;
	/**
	 * 户口本编号
	 */
	private String householdRegisterNumber;
	/**
	 * 补贴标准
	 */
	private Double subsidyStandard;
	/**
	 * 确权面积
	 */
	private Double confirmArea;
	/**
	 * 六不补面积
	 */
	private Double noSubsidyArea;
	/**
	 * 补贴面积
	 */
	@Field("SUBSIDY_AREA")
	private Double subsidyArea;
	/**
	 * 补贴金额
	 */
	@Field("SUBSIDY_MONEY")
	private Double subsidyMoney;
	/**
	 * 银行卡号
	 */
	private String bankCardNumber;
	/**
	 * 开户行名称
	 */
	private String bankName;
	/**
	 * 收款行行号
	 */
	private String bankCode;
	/**
	 * 受补贴人电话
	 */
	private String contactNumber;
	/**
	 * 省id
	 */
	private String provinceId;
	/**
	 * 市id
	 */
	private String cityId;
	/**
	 * 县id
	 */
	private String countyId;
	/**
	 * 乡镇id
	 */
	private String townId;
	/**
	 * 村id
	 */
	private String villageId;
	/**
	 * 组id
	 */
	private String groupId;
	/**
	 * 省名称
	 */
	private String provinceName;
	/**
	 * 市名称
	 */
	private String cityName;
	/**
	 * 县名称
	 */
	private String countyName;
	/**
	 * 乡镇名称
	 */
	private String townName;
	/**
	 * 村名称
	 */
	private String villageName;
	/**
	 * 组名称
	 */
	private String groupName;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 修正确权面积
	 */
	private Double confirmAreaM;
	/**
	 * 修正六不补面积
	 */
	private Double noSubsidyAreaM;
	/**
	 * 修正补贴面积
	 */
	private Double subsidyAreaM;
	/**
	 * 修正补贴金额
	 */
	private Double subsidyMoneyM;
	/**
	 * 修正时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateSubsidyDate;
	/**
	 * 修正人ID
	 */
	private String updateSubsidyUserId;

	/**
	 * 修正人名称
	 */
	private String updateSubsidyUserName;
	/**
	 * 数据状态，1为初始，2为修改，3为新增，4为删除
	 */
	private String dataState;

	/**
	 * 补贴年度
	 */
	private String subsidyYear;

	/**
	 * 畜牧养殖场地用地面积
	 */
	private Double noLivestockArea;
	/**
	 * 颁发林权证和退耕还林土地面积
	 */
	private Double noForestOwnerArea;
	/**
	 * 成为转为设施农业用地面积
	 */
	private Double noFacilitiesArea;
	/**
	 * 非农业征用占地面积
	 */
	private Double noAgricultureArea;
	/**
	 * 常年抛(撂)耕地面积
	 */
	private Double noAbandonArea;
	/**
	 * 质量达不到耕种条件面积
	 */
	private Double noCultivationArea;
	/**
	 * 修正畜牧养殖场地用地面积
	 */
	private Double noLivestockAreaM;
	/**
	 * 修正颁发林权证和退耕还林土地面积
	 */
	private Double noForestOwnerAreaM;
	/**
	 * 修正成为转为设施农业用地面积
	 */
	private Double noFacilitiesAreaM;
	/**
	 * 修正非农业征用占地面积
	 */
	private Double noAgricultureAreaM;
	/**
	 * 修正常年抛(撂)耕地面积
	 */
	private Double noAbandonAreaM;
	/**
	 * 修正质量达不到耕种条件面积
	 */
	private Double noCultivationAreaM;

	/**
	 * 承包国有农场和良种场已确权的耕地
	 */
	private	Double stateOwnArea;
	/**
	 * 流转转入的耕地
	 */
	private	Double changeIntoArea;
	/**
	 * 流转转出的耕地
	 */
	private	Double changeOutArea;
	/**
	 * 修正承包国有农场和良种场已确权的耕地
	 */
	private	Double stateOwnAreaM;
	/**
	 * 修正流转转入的耕地
	 */
	private	Double changeIntoAreaM;
	/**
	 * 修正流转转出的耕地
	 */
	private	Double changeOutAreaM;


	public String getSubsidyYear() {
		return subsidyYear;
	}

	public void setSubsidyYear(String subsidyYear) {
		this.subsidyYear = subsidyYear;
	}
	public String getUpdateSubsidyUserName() {
		return updateSubsidyUserName;
	}

	public void setUpdateSubsidyUserName(String updateSubsidyUserName) {
		this.updateSubsidyUserName = updateSubsidyUserName;
	}
	/**
	 * 设置：户ID
	 */
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	/**
	 * 获取：户ID
	 */
	public String getFamilyId() {
		return familyId;
	}
	/**
	 * 设置：户编号
	 */
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	/**
	 * 获取：户编号
	 */
	public String getFamilyCode() {
		return familyCode;
	}
	/**
	 * 设置：农民姓名
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * 获取：农民姓名
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * 设置：农民身份证号
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * 获取：农民身份证号
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * 设置：户口本编号
	 */
	public void setHouseholdRegisterNumber(String householdRegisterNumber) {
		this.householdRegisterNumber = householdRegisterNumber;
	}
	/**
	 * 获取：户口本编号
	 */
	public String getHouseholdRegisterNumber() {
		return householdRegisterNumber;
	}
	/**
	 * 设置：补贴标准
	 */
	public void setSubsidyStandard(Double subsidyStandard) {
		this.subsidyStandard = subsidyStandard;
	}
	/**
	 * 获取：补贴标准
	 */
	public Double getSubsidyStandard() {
		return subsidyStandard;
	}
	/**
	 * 设置：确权面积
	 */
	public void setConfirmArea(Double confirmArea) {
		this.confirmArea = confirmArea;
	}
	/**
	 * 获取：确权面积
	 */
	public Double getConfirmArea() {
		return confirmArea;
	}
	/**
	 * 设置：六不补面积
	 */
	public void setNoSubsidyArea(Double noSubsidyArea) {
		this.noSubsidyArea = noSubsidyArea;
	}
	/**
	 * 获取：六不补面积
	 */
	public Double getNoSubsidyArea() {
		return noSubsidyArea;
	}
	/**
	 * 设置：补贴面积
	 */
	public void setSubsidyArea(Double subsidyArea) {
		this.subsidyArea = subsidyArea;
	}
	/**
	 * 获取：补贴面积
	 */
	public Double getSubsidyArea() {
		return subsidyArea;
	}
	/**
	 * 设置：补贴金额
	 */
	public void setSubsidyMoney(Double subsidyMoney) {
		this.subsidyMoney = subsidyMoney;
	}
	/**
	 * 获取：补贴金额
	 */
	public Double getSubsidyMoney() {
		return subsidyMoney;
	}
	/**
	 * 设置：银行卡号
	 */
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	/**
	 * 设置：开户行名称
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * 获取：开户行名称
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * 设置：收款行行号
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * 获取：收款行行号
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * 设置：受补贴人电话
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * 获取：受补贴人电话
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * 设置：省id
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省id
	 */
	public String getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：市id
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市id
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * 设置：县id
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	/**
	 * 获取：县id
	 */
	public String getCountyId() {
		return countyId;
	}
	/**
	 * 设置：乡镇id
	 */
	public void setTownId(String townId) {
		this.townId = townId;
	}
	/**
	 * 获取：乡镇id
	 */
	public String getTownId() {
		return townId;
	}
	/**
	 * 设置：村id
	 */
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	/**
	 * 获取：村id
	 */
	public String getVillageId() {
		return villageId;
	}
	/**
	 * 设置：组id
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * 获取：组id
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * 设置：省名称
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：省名称
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * 设置：市名称
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：市名称
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：县名称
	 */
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	/**
	 * 获取：县名称
	 */
	public String getCountyName() {
		return countyName;
	}
	/**
	 * 设置：乡镇名称
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}
	/**
	 * 获取：乡镇名称
	 */
	public String getTownName() {
		return townName;
	}
	/**
	 * 设置：村名称
	 */
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	/**
	 * 获取：村名称
	 */
	public String getVillageName() {
		return villageName;
	}
	/**
	 * 设置：组名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 获取：组名称
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * 设置：状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：修正确权面积
	 */
	public void setConfirmAreaM(Double confirmAreaM) {
		this.confirmAreaM = confirmAreaM;
	}
	/**
	 * 获取：修正确权面积
	 */
	public Double getConfirmAreaM() {
		return confirmAreaM;
	}
	/**
	 * 设置：修正六不补面积
	 */
	public void setNoSubsidyAreaM(Double noSubsidyAreaM) {
		this.noSubsidyAreaM = noSubsidyAreaM;
	}
	/**
	 * 获取：修正六不补面积
	 */
	public Double getNoSubsidyAreaM() {
		return noSubsidyAreaM;
	}
	/**
	 * 设置：修正补贴面积
	 */
	public void setSubsidyAreaM(Double subsidyAreaM) {
		this.subsidyAreaM = subsidyAreaM;
	}
	/**
	 * 获取：修正补贴面积
	 */
	public Double getSubsidyAreaM() {
		return subsidyAreaM;
	}
	/**
	 * 设置：修正补贴金额
	 */
	public void setSubsidyMoneyM(Double subsidyMoneyM) {
		this.subsidyMoneyM = subsidyMoneyM;
	}
	/**
	 * 获取：修正补贴金额
	 */
	public Double getSubsidyMoneyM() {
		return subsidyMoneyM;
	}
	/**
	 * 设置：修正时间
	 */
	public void setUpdateSubsidyDate(Date updateSubsidyDate) {
		this.updateSubsidyDate = updateSubsidyDate;
	}
	/**
	 * 获取：修正时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateSubsidyDate() {
		return updateSubsidyDate;
	}
	/**
	 * 设置：修正人
	 */
	public void setUpdateSubsidyUserId(String updateSubsidyUserId) {
		this.updateSubsidyUserId = updateSubsidyUserId;
	}
	/**
	 * 获取：修正人
	 */
	public String getUpdateSubsidyUserId() {
		return updateSubsidyUserId;
	}
	/**
	 * 设置：数据状态，1为初始，2为修改，3为新增，4为删除
	 */
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	/**
	 * 获取：数据状态，1为初始，2为修改，3为新增，4为删除
	 */
	public String getDataState() {
		return dataState;
	}

	/**
	 * 设置：畜牧养殖场地用地面积
	 */
	public void setNoLivestockArea(Double noLivestockArea) {
		this.noLivestockArea = noLivestockArea;
	}
	/**
	 * 获取：畜牧养殖场地用地面积
	 */
	public Double getNoLivestockArea() {
		return noLivestockArea;
	}
	/**
	 * 设置：颁发林权证和退耕还林土地面积
	 */
	public void setNoForestOwnerArea(Double noForestOwnerArea) {
		this.noForestOwnerArea = noForestOwnerArea;
	}
	/**
	 * 获取：颁发林权证和退耕还林土地面积
	 */
	public Double getNoForestOwnerArea() {
		return noForestOwnerArea;
	}
	/**
	 * 设置：成为转为设施农业用地面积
	 */
	public void setNoFacilitiesArea(Double noFacilitiesArea) {
		this.noFacilitiesArea = noFacilitiesArea;
	}
	/**
	 * 获取：成为转为设施农业用地面积
	 */
	public Double getNoFacilitiesArea() {
		return noFacilitiesArea;
	}
	/**
	 * 设置：非农业征用占地面积
	 */
	public void setNoAgricultureArea(Double noAgricultureArea) {
		this.noAgricultureArea = noAgricultureArea;
	}
	/**
	 * 获取：非农业征用占地面积
	 */
	public Double getNoAgricultureArea() {
		return noAgricultureArea;
	}
	/**
	 * 设置：常年抛(撂)耕地面积
	 */
	public void setNoAbandonArea(Double noAbandonArea) {
		this.noAbandonArea = noAbandonArea;
	}
	/**
	 * 获取：常年抛(撂)耕地面积
	 */
	public Double getNoAbandonArea() {
		return noAbandonArea;
	}
	/**
	 * 设置：质量达不到耕种条件面积
	 */
	public void setNoCultivationArea(Double noCultivationArea) {
		this.noCultivationArea = noCultivationArea;
	}
	/**
	 * 获取：质量达不到耕种条件面积
	 */
	public Double getNoCultivationArea() {
		return noCultivationArea;
	}
	/**
	 * 设置：修正畜牧养殖场地用地面积
	 */
	public void setNoLivestockAreaM(Double noLivestockAreaM) {
		this.noLivestockAreaM = noLivestockAreaM;
	}
	/**
	 * 获取：修正畜牧养殖场地用地面积
	 */
	public Double getNoLivestockAreaM() {
		return noLivestockAreaM;
	}
	/**
	 * 设置：修正颁发林权证和退耕还林土地面积
	 */
	public void setNoForestOwnerAreaM(Double noForestOwnerAreaM) {
		this.noForestOwnerAreaM = noForestOwnerAreaM;
	}
	/**
	 * 获取：修正颁发林权证和退耕还林土地面积
	 */
	public Double getNoForestOwnerAreaM() {
		return noForestOwnerAreaM;
	}
	/**
	 * 设置：修正成为转为设施农业用地面积
	 */
	public void setNoFacilitiesAreaM(Double noFacilitiesAreaM) {
		this.noFacilitiesAreaM = noFacilitiesAreaM;
	}
	/**
	 * 获取：修正成为转为设施农业用地面积
	 */
	public Double getNoFacilitiesAreaM() {
		return noFacilitiesAreaM;
	}
	/**
	 * 设置：修正非农业征用占地面积
	 */
	public void setNoAgricultureAreaM(Double noAgricultureAreaM) {
		this.noAgricultureAreaM = noAgricultureAreaM;
	}
	/**
	 * 获取：修正非农业征用占地面积
	 */
	public Double getNoAgricultureAreaM() {
		return noAgricultureAreaM;
	}
	/**
	 * 设置：修正常年抛(撂)耕地面积
	 */
	public void setNoAbandonAreaM(Double noAbandonAreaM) {
		this.noAbandonAreaM = noAbandonAreaM;
	}
	/**
	 * 获取：修正常年抛(撂)耕地面积
	 */
	public Double getNoAbandonAreaM() {
		return noAbandonAreaM;
	}
	/**
	 * 设置：修正质量达不到耕种条件面积
	 */
	public void setNoCultivationAreaM(Double noCultivationAreaM) {
		this.noCultivationAreaM = noCultivationAreaM;
	}
	/**
	 * 获取：修正质量达不到耕种条件面积
	 */
	public Double getNoCultivationAreaM() {
		return noCultivationAreaM;
	}

	public Double getStateOwnArea() {
		return stateOwnArea;
	}

	public void setStateOwnArea(Double stateOwnArea) {
		this.stateOwnArea = stateOwnArea;
	}

	public Double getChangeIntoArea() {
		return changeIntoArea;
	}

	public void setChangeIntoArea(Double changeIntoArea) {
		this.changeIntoArea = changeIntoArea;
	}

	public Double getChangeOutArea() {
		return changeOutArea;
	}

	public void setChangeOutArea(Double changeOutArea) {
		this.changeOutArea = changeOutArea;
	}

	public Double getStateOwnAreaM() {
		return stateOwnAreaM;
	}

	public void setStateOwnAreaM(Double stateOwnAreaM) {
		this.stateOwnAreaM = stateOwnAreaM;
	}

	public Double getChangeIntoAreaM() {
		return changeIntoAreaM;
	}

	public void setChangeIntoAreaM(Double changeIntoAreaM) {
		this.changeIntoAreaM = changeIntoAreaM;
	}

	public Double getChangeOutAreaM() {
		return changeOutAreaM;
	}

	public void setChangeOutAreaM(Double changeOutAreaM) {
		this.changeOutAreaM = changeOutAreaM;
	}
}
