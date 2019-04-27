package com.revature.bank.joshparkerj.db;

import java.util.LinkedList;
import java.util.List;

public class AccountHolderDBHandler implements IDB.accountholders {

	private StringBuilder s;
	private List<AccountHolder> accountholders;

	public AccountHolderDBHandler(List<AccountHolder> ah) {
		accountholders = ah;
	}

	public String serialize() {
		s = new StringBuilder();
		accountholders.forEach(ah -> this.s.append(ah.serialize()));
		return s.toString();
	}

	public void addAccountHolder(String id, String num) {
		accountholders.add(new AccountHolder(id, num, true));
	}

	public void addJointAccount(String id, String num) {
		accountholders.add(new AccountHolder(id, num, false));
	}

	public void approveAccountHolder(String id, String num) {
		for (AccountHolder ah : accountholders) {
			if (ah.getNum().equals(num) && ah.getSSN().equals(id)) {
				ah.approve();
			}
		}
	}

	public void denyJointApp(String id, String num) {
		accountholders.removeIf(ah -> ah.getSSN().equals(id) && ah.getNum().equals(num));
	}

	public boolean jointAppExists(String id, String num) {
		for (AccountHolder ah : accountholders) {
			if (ah.getNum().equals(num) && ah.getSSN().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public String getJointApps(String id) {
		s = new StringBuilder();
		for (AccountHolder ah : accountholders) {
			if (ah.getSSN().equals(id)) {
				for (AccountHolder zl : accountholders) {
					if (zl.getNum().equals(ah.getNum()) && !zl.isApproved()) {
						s.append(zl.serialize());
					}
				}
			}
		}
		return s.toString();
	}

	public boolean holdsAccount(String id, String num) {
		for (AccountHolder ah : accountholders) {
			if (ah.getNum().equals(num) && ah.getSSN().equals(id)) {
				return ah.isApproved();
			}
		}
		return false;
	}

	public void removeByNum(String num) {
		accountholders.removeIf(ah->ah.getNum().equals(num));
	}

	public void removeBySSN(String id) {
		accountholders.removeIf(ah->ah.getSSN().equals(id));
	}

	public boolean isEmpty() {
		return accountholders.isEmpty();
	}

	public List<AccountHolder> getBySSN(String id) {
		List<AccountHolder> lah = new LinkedList<AccountHolder>();
		for (AccountHolder ah : accountholders) {
			if (ah.getSSN().equals(id)) lah.add(ah);
		}
		return lah;
	}

	public List<AccountHolder> getByNum(String id) {
		List<AccountHolder> lah = new LinkedList<AccountHolder>();
		for (AccountHolder ah : accountholders) {
			if (ah.getNum().equals(id)) lah.add(ah);
		}
		return lah;
	}

}
