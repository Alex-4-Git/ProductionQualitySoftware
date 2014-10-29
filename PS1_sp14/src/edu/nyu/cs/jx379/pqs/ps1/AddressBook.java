package edu.nyu.cs.jx379.pqs.ps1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 
 * The address book class
 * @author Jing Xia
 *
 */
public class AddressBook {
	private List<AddressBookEntry> addressbookcontent;
	private final String FILE_NAME = "content.csv";
	private final static Logger LOGGER = Logger
			.getLogger(AddressBook.class.getName());
	/**
	 * Construction function. To initial this address book by a ArrayList.
	 */
	public AddressBook() {
		addressbookcontent = new ArrayList<AddressBookEntry>();
	}

	/**
	 * Add a new entry into this address book.
	 * 
	 * @param name     	required
	 * @param address  	optional
	 * @param phone		optional
	 * @param email		optional
	 * @param note		optional
	 * @return if nothing wrong for adding entry, then return true. Else return false like name is
	 *         empty.
	 */
	public boolean addEntry(String name, String address, String phone,
			String email, String note) {
		AddressBookEntry entry = null;
		try {
			entry = new AddressBookEntry.Builder(name).address(address)
					.email(email).phone(phone).note(note).build();
		} catch (RequiredFieldEmptyException e) {
			e.printStackTrace();
		}
		if (entry != null) {
			addressbookcontent.add(entry);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Use the searchEntryByField function to locate the entry. Then delete it
	 * from the container.
	 * 
	 * @param searchMap
	 * @return if nothing wrong, then true. else false.
	 */
	public boolean removeEntry(Map<String, String> searchMap) {
		AddressBookEntry entry = this.searchEntryByField(searchMap);
		if (entry != null) {
			addressbookcontent.remove(entry);
			return true;
		} else {
			LOGGER.severe("Cannot find this entry by search fields.");
//			System.err.println("Cannot find this entry by search fields.");
			return false;
		}
	}

	/**
	 * 
	 * The search function input is a searchMap which contains key [name|address|phone|email|note] and value for these fields.
	 * @param searchMap
	 * @return The entry.
	 */
	public AddressBookEntry searchEntryByField(Map<String, String> searchMap) {
		for (AddressBookEntry entry : addressbookcontent) {
			if (searchMap.containsKey("name")) {
				if (entry.getName().equals(searchMap.get("name"))) {
					return entry;
				}
			} else if (searchMap.containsKey("address")) {
				if (entry.getAddress().equals(searchMap.get("address"))) {
					return entry;
				}
			} else if (searchMap.containsKey("phone")) {
				if (entry.getPhone().equals(searchMap.get("phone"))) {
					return entry;
				}
			} else if (searchMap.containsKey("email")) {
				if (entry.getEmail().equals(searchMap.get("email"))) {
					return entry;
				}
			} else if (searchMap.containsKey("note")) {
				if (entry.getNote().equals(searchMap.get("note"))) {
					return entry;
				}
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * Save this AddressBook to file in local folder named by variable FILE_NAME.
	 * @return if success saved into file, then true, else false.
	 */
	public boolean saveAddressBook() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name|Address|Phone|Email|Note\n");
		for (AddressBookEntry entry : addressbookcontent) {
			sb.append(entry.getName());
			sb.append("|");
			sb.append(entry.getAddress());
			sb.append("|");
			sb.append(entry.getPhone());
			sb.append("|");
			sb.append(entry.getEmail());
			sb.append("|");
			sb.append(entry.getNote());
			sb.append("\n");
		}
		if (Utils.saveFile(FILE_NAME, sb.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Read from local folder filename FILE_NAME, then overwrite original addressbook.
	 */
	public void readAddressBook() {
		// overwrite original addressbook
		String content = Utils.readFile(FILE_NAME);
		String[] lines = content.split("\n");
		if (lines.length > 1) {
			int count = 0;
			for (String line : lines) {
				if (count == 0) {
					count++;
					addressbookcontent = new ArrayList<AddressBookEntry>();
					continue;
				} else {
					String[] word = line.split("|");
					if (word.length == 5) {
						try {
							addressbookcontent.add(new AddressBookEntry.Builder(
									word[0]).address(word[1]).phone(word[2])
									.email(word[3]).note(word[4]).build());
						} catch (RequiredFieldEmptyException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
