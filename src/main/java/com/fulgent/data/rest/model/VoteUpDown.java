package com.fulgent.data.rest.model;

public class VoteUpDown {
	private String userid;
	private long did;
	private boolean voted;

	public VoteUpDown() {}

	public VoteUpDown(String userid, long did, boolean voted) {
		this.userid = userid;
		this.did = did;
		this.voted = voted;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public long getDid() {
		return did;
	}

	public void setDid(long did) {
		this.did = did;
	}

	public boolean isVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	@Override
	public String toString() {
		return "VoteUpDown [userid=" + userid + ", did=" + did + ", voted=" + voted + "]";
	}

}
