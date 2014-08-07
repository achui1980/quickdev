package com.achui.quick.common.entity;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;

public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID> {
	
	public abstract ID getId();
	
	public abstract void setId(final ID id);
	
	public boolean isNew(){
		return null == getId();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(!getClass().equals(obj.getClass())) return false;
		AbstractEntity<?> that = (AbstractEntity<?>)obj;
		return that.getId() == null ? false : this.getId().equals(that.getId());
	}
	
	 /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

}
