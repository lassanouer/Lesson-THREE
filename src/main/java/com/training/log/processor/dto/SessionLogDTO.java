package com.training.log.processor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Anouer Lassoued
 *
 */
public class SessionLogDTO implements Serializable {

	private static final long serialVersionUID = -2802823882278050893L;

	private String mIp;
	private Date mD_Connection;
	private Date mD_Deconnection;
	private long mDuree;
	private List<String> mListRequetes;
	private List<String> mListUrl;
	private long mNbrPagesVisitees;

	public SessionLogDTO(String mIp, Date mD_Connection, String iRequete, String iUrl) {
		super();
		this.mIp = mIp;
		this.mD_Connection = mD_Connection;
		this.mNbrPagesVisitees = 1;
		
		List<String> llistRequest = new ArrayList<String>();
		llistRequest.add(iRequete);
		this.mListRequetes = llistRequest;

		List<String> llistUrl = new ArrayList<>();
		llistUrl.add(iUrl);
		this.mListUrl = llistUrl;
	}

	public SessionLogDTO(String mIp, Date mD_Connection, Date mD_Deconnection, long mDuree, List<String> mListRequetes,
			List<String> mListUrl, long mNbrPagesVisitees) {
		super();
		this.mIp = mIp;
		this.mD_Connection = mD_Connection;
		this.mD_Deconnection = mD_Deconnection;
		this.mDuree = mDuree;
		this.mListRequetes = mListRequetes;
		this.mListUrl = mListUrl;
		this.mNbrPagesVisitees = mNbrPagesVisitees;
	}

	public SessionLogDTO() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mD_Connection == null) ? 0 : mD_Connection.hashCode());
		result = prime * result + ((mD_Deconnection == null) ? 0 : mD_Deconnection.hashCode());
		result = prime * result + (int) (mDuree ^ (mDuree >>> 32));
		result = prime * result + ((mIp == null) ? 0 : mIp.hashCode());
		result = prime * result + ((mListRequetes == null) ? 0 : mListRequetes.hashCode());
		result = prime * result + ((mListUrl == null) ? 0 : mListUrl.hashCode());
		result = prime * result + (int) (mNbrPagesVisitees ^ (mNbrPagesVisitees >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionLogDTO other = (SessionLogDTO) obj;
		if (mD_Connection == null) {
			if (other.mD_Connection != null)
				return false;
		} else if (!mD_Connection.equals(other.mD_Connection))
			return false;
		if (mD_Deconnection == null) {
			if (other.mD_Deconnection != null)
				return false;
		} else if (!mD_Deconnection.equals(other.mD_Deconnection))
			return false;
		if (mDuree != other.mDuree)
			return false;
		if (mIp == null) {
			if (other.mIp != null)
				return false;
		} else if (!mIp.equals(other.mIp))
			return false;
		if (mListRequetes == null) {
			if (other.mListRequetes != null)
				return false;
		} else if (!mListRequetes.equals(other.mListRequetes))
			return false;
		if (mListUrl == null) {
			if (other.mListUrl != null)
				return false;
		} else if (!mListUrl.equals(other.mListUrl))
			return false;
		if (mNbrPagesVisitees != other.mNbrPagesVisitees)
			return false;
		return true;
	}

	/*
	 * Getters & Setters
	 */
	public String getmIp() {
		return mIp;
	}

	public void setmIp(String mIp) {
		this.mIp = mIp;
	}

	public Date getmD_Connection() {
		return mD_Connection;
	}

	public void setmD_Connection(Date mD_Connection) {
		this.mD_Connection = mD_Connection;
	}

	public Date getmD_Deconnection() {
		return mD_Deconnection;
	}

	public void setmD_Deconnection(Date mD_Deconnection) {
		this.mD_Deconnection = mD_Deconnection;
	}

	public long getmDuree() {
		return mDuree;
	}

	public void setmDuree(long mDuree) {
		this.mDuree = mDuree;
	}

	public List<String> getmListRequetes() {
		return mListRequetes;
	}

	public void setmListRequetes(List<String> mListRequetes) {
		this.mListRequetes = mListRequetes;
	}

	public List<String> getmListUrl() {
		return mListUrl;
	}

	public void setmListUrl(List<String> mListUrl) {
		this.mListUrl = mListUrl;
	}

	public long getmNbrPagesVisitees() {
		return mNbrPagesVisitees;
	}

	public void setmNbrPagesVisitees(long mNbrPagesVisitees) {
		this.mNbrPagesVisitees = mNbrPagesVisitees;
	}

}
