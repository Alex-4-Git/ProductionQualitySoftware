package edu.nyu.cs.jx379.pqs.ps1;

import java.util.logging.Logger;

//Change to use builder pattern, overrides toString, hashCode and equals function.
//Check field for avoiding error parsing.
/**
 * 
 * Entry Class for Address Book. Check input throw Exception if invalid input.
 * @author Jing Xia
 *
 */
public class AddressBookEntry {
	private final static Logger LOGGER = Logger
			.getLogger(AddressBookEntry.class.getName());
	/** 
	 * Return formatted AddressBookEntry.
	 * Format:  "AddressBookEntry [name=" + name + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", note=" + note
				+ "]"
	 */
	@Override
	public String toString() {
		return "AddressBookEntry [name=" + name + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", note=" + note
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	/**
	 * Overrides the equals for simple judgment.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AddressBookEntry))
			return false;
		AddressBookEntry entry = (AddressBookEntry) obj;
		return this.toString().equals(entry.toString());
	}

	private final String name;
	private final String address;
	private final String phone;
	private final String email;
	private final String note;

	/**
	 * 
	 * Inner Builder class for Builder Pattern.
	 * @author Jing Xia
	 * 
	 */
	public static class Builder {
		// required fields
		private final String name;

		// Optional fields
		private String address = "";
		private String phone = "";
		private String email = "";
		private String note = "";

		/**
		 * input & check name
		 * @param name
		 * @throws RequiredFieldEmptyException
		 */
		public Builder(String name) throws RequiredFieldEmptyException {
			if (name == null || "".equals(name.trim())) {
				LOGGER.severe("Name cannot be empty.");
//				System.err.println("Name cannot be empty.");
				throw new RequiredFieldEmptyException();
			}		
			checkField(name);
			this.name = name;
		}

		/**
		 * input & check address
		 * @param val
		 * @return
		 */
		public Builder address(String val) {
			checkField(val);
			address = val;
			return this;
		}

		/**
		 * input & check phone
		 * @param val
		 * @return
		 */
		public Builder phone(String val) {
			checkField(val);
			phone = val;
			return this;
		}

		/**
		 * input & check email
		 * @param val
		 * @return
		 */
		public Builder email(String val) {
			checkField(val);
			email = val;
			return this;
		}

		/**
		 * input & check note.
		 * @param val
		 * @return
		 */
		public Builder note(String val) {
			checkField(val);
			note = val;
			return this;
		}

		/**
		 * @return AddressBookEntry
		 */
		public AddressBookEntry build() {
			return new AddressBookEntry(this);
		}
		
		private void checkField(String str){
			if(str.contains("|")||str.contains("\n")){
				LOGGER.severe("Please do not input \"|\" or \"\\n\"");
//				System.err.println("Please do not input \"|\" or \"\\n\"");
				throw new IllegalArgumentException();
			}
		}

	}

	/**
	 * Construction function with all fields but private.
	 * 
	 */
	private AddressBookEntry(Builder builder) {
		name = builder.name;
		address = builder.address;
		phone = builder.phone;
		email = builder.email;
		note = builder.note;
	}

	/**
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return String address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return String phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return String note
	 */
	public String getNote() {
		return note;
	}

}
