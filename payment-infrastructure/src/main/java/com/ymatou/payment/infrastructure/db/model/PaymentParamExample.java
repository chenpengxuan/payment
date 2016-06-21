package com.ymatou.payment.infrastructure.db.model;

import java.util.ArrayList;
import java.util.List;

public class PaymentParamExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public PaymentParamExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andParamIdIsNull() {
            addCriterion("ParamId is null");
            return (Criteria) this;
        }

        public Criteria andParamIdIsNotNull() {
            addCriterion("ParamId is not null");
            return (Criteria) this;
        }

        public Criteria andParamIdEqualTo(Integer value) {
            addCriterion("ParamId =", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotEqualTo(Integer value) {
            addCriterion("ParamId <>", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThan(Integer value) {
            addCriterion("ParamId >", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ParamId >=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThan(Integer value) {
            addCriterion("ParamId <", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThanOrEqualTo(Integer value) {
            addCriterion("ParamId <=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdIn(List<Integer> values) {
            addCriterion("ParamId in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotIn(List<Integer> values) {
            addCriterion("ParamId not in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdBetween(Integer value1, Integer value2) {
            addCriterion("ParamId between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ParamId not between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamCatIsNull() {
            addCriterion("ParamCat is null");
            return (Criteria) this;
        }

        public Criteria andParamCatIsNotNull() {
            addCriterion("ParamCat is not null");
            return (Criteria) this;
        }

        public Criteria andParamCatEqualTo(String value) {
            addCriterion("ParamCat =", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatNotEqualTo(String value) {
            addCriterion("ParamCat <>", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatGreaterThan(String value) {
            addCriterion("ParamCat >", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatGreaterThanOrEqualTo(String value) {
            addCriterion("ParamCat >=", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatLessThan(String value) {
            addCriterion("ParamCat <", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatLessThanOrEqualTo(String value) {
            addCriterion("ParamCat <=", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatLike(String value) {
            addCriterion("ParamCat like", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatNotLike(String value) {
            addCriterion("ParamCat not like", value, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatIn(List<String> values) {
            addCriterion("ParamCat in", values, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatNotIn(List<String> values) {
            addCriterion("ParamCat not in", values, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatBetween(String value1, String value2) {
            addCriterion("ParamCat between", value1, value2, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamCatNotBetween(String value1, String value2) {
            addCriterion("ParamCat not between", value1, value2, "paramCat");
            return (Criteria) this;
        }

        public Criteria andParamKeyIsNull() {
            addCriterion("ParamKey is null");
            return (Criteria) this;
        }

        public Criteria andParamKeyIsNotNull() {
            addCriterion("ParamKey is not null");
            return (Criteria) this;
        }

        public Criteria andParamKeyEqualTo(String value) {
            addCriterion("ParamKey =", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotEqualTo(String value) {
            addCriterion("ParamKey <>", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThan(String value) {
            addCriterion("ParamKey >", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThanOrEqualTo(String value) {
            addCriterion("ParamKey >=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThan(String value) {
            addCriterion("ParamKey <", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThanOrEqualTo(String value) {
            addCriterion("ParamKey <=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLike(String value) {
            addCriterion("ParamKey like", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotLike(String value) {
            addCriterion("ParamKey not like", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyIn(List<String> values) {
            addCriterion("ParamKey in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotIn(List<String> values) {
            addCriterion("ParamKey not in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyBetween(String value1, String value2) {
            addCriterion("ParamKey between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotBetween(String value1, String value2) {
            addCriterion("ParamKey not between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamStrValueIsNull() {
            addCriterion("ParamStrValue is null");
            return (Criteria) this;
        }

        public Criteria andParamStrValueIsNotNull() {
            addCriterion("ParamStrValue is not null");
            return (Criteria) this;
        }

        public Criteria andParamStrValueEqualTo(String value) {
            addCriterion("ParamStrValue =", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueNotEqualTo(String value) {
            addCriterion("ParamStrValue <>", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueGreaterThan(String value) {
            addCriterion("ParamStrValue >", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueGreaterThanOrEqualTo(String value) {
            addCriterion("ParamStrValue >=", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueLessThan(String value) {
            addCriterion("ParamStrValue <", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueLessThanOrEqualTo(String value) {
            addCriterion("ParamStrValue <=", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueLike(String value) {
            addCriterion("ParamStrValue like", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueNotLike(String value) {
            addCriterion("ParamStrValue not like", value, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueIn(List<String> values) {
            addCriterion("ParamStrValue in", values, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueNotIn(List<String> values) {
            addCriterion("ParamStrValue not in", values, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueBetween(String value1, String value2) {
            addCriterion("ParamStrValue between", value1, value2, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamStrValueNotBetween(String value1, String value2) {
            addCriterion("ParamStrValue not between", value1, value2, "paramStrValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueIsNull() {
            addCriterion("ParamIntValue is null");
            return (Criteria) this;
        }

        public Criteria andParamIntValueIsNotNull() {
            addCriterion("ParamIntValue is not null");
            return (Criteria) this;
        }

        public Criteria andParamIntValueEqualTo(Integer value) {
            addCriterion("ParamIntValue =", value, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueNotEqualTo(Integer value) {
            addCriterion("ParamIntValue <>", value, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueGreaterThan(Integer value) {
            addCriterion("ParamIntValue >", value, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("ParamIntValue >=", value, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueLessThan(Integer value) {
            addCriterion("ParamIntValue <", value, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueLessThanOrEqualTo(Integer value) {
            addCriterion("ParamIntValue <=", value, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueIn(List<Integer> values) {
            addCriterion("ParamIntValue in", values, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueNotIn(List<Integer> values) {
            addCriterion("ParamIntValue not in", values, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueBetween(Integer value1, Integer value2) {
            addCriterion("ParamIntValue between", value1, value2, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andParamIntValueNotBetween(Integer value1, Integer value2) {
            addCriterion("ParamIntValue not between", value1, value2, "paramIntValue");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("Memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("Memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("Memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("Memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("Memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("Memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("Memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("Memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("Memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("Memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("Memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("Memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("Memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("Memo not between", value1, value2, "memo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated do_not_delete_during_merge Mon Jun 13 13:47:39 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PP_PaymentParam
     *
     * @mbggenerated Mon Jun 13 13:47:39 CST 2016
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}