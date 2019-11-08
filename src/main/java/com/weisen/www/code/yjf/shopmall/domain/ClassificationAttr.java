package com.weisen.www.code.yjf.shopmall.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "classification_attr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClassificationAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname ClassificationAttr.
     */
    @ApiModelProperty(value = "The firstname ClassificationAttr.")
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "aid")
    private Integer aid;

    @Column(name = "avid")
    private Integer avid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public ClassificationAttr cid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getAid() {
        return aid;
    }

    public ClassificationAttr aid(Integer aid) {
        this.aid = aid;
        return this;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAvid() {
        return avid;
    }

    public ClassificationAttr avid(Integer avid) {
        this.avid = avid;
        return this;
    }

    public void setAvid(Integer avid) {
        this.avid = avid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassificationAttr)) {
            return false;
        }
        return id != null && id.equals(((ClassificationAttr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClassificationAttr{" +
            "id=" + getId() +
            ", cid=" + getCid() +
            ", aid=" + getAid() +
            ", avid=" + getAvid() +
            "}";
    }
}
