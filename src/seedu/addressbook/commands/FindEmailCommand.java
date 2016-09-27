package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

public class FindEmailCommand extends Command {

    public static final String COMMAND_WORD = "findemail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose emails contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " alice@gmail.com google.com charlie@bing.com";

    private final Set<String> keywords;

    public FindEmailCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithEmailContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithEmailContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        Object[] keywordArray = keywords.toArray();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
        	for (Object keyword : keywordArray) {
        		System.out.println("Checking for emails");
        		if (person.getEmail().toString().contains((String)keyword)) {
        			matchedPersons.add(person);
        			break;
        		}
        	}
        }
        return matchedPersons;
    }

}
