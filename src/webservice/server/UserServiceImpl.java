package webservice.server;

import javax.jws.WebService;
import java.util.*;


@WebService(endpointInterface = "UserService")
public class UserServiceImpl implements UserService {
    private final List<User> registry = new ArrayList<>();

    @Override
    public User getQuestion() {
        Random random = new Random();
        String name = generateName();
        String dob = String.format("%d/%d/%d", random.nextInt(31), random.nextInt(12), random.nextInt(23) + 2000);
        User tmp = new User(0, name, dob);
        registry.add(tmp);
        return tmp;
    }

    private String generateName() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        // Generate a random number of words (between 3 and 8)
        int numWords = random.nextInt(6) + 3;

        for (int i = 0; i < numWords; i++) {
            // Generate a random word length (between 5 and 10 characters)
            int wordLength = random.nextInt(6) + 5;

            // Generate a random word with mixed case and alphanumeric characters
            String randomWord = generateRandomWord(wordLength);

            // Append the word to the string builder
            stringBuilder.append(randomWord);

            // Add a space between words (except for the last word)
            if (i < numWords - 1) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

    private String generateRandomWord(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Generate a random character (uppercase letter, lowercase letter, or digit)
            char randomChar = (char) (random.nextInt(57) + 65);

            // Append the character to the string builder
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    @Override
    public void submitAnswer(User user) {
        try {
            User original = registry.stream().filter(it -> Objects.equals(it.getId(), user.getId())).findFirst().get();
            String correctUsername = solveUsername(original.getUsername());
            String correctDob = solveDob(original.getBirthday());
            if (user.getUsername() == correctUsername && user.getBirthday() == correctDob)
                System.out.println("CORRECT\n");
            else
                System.out.printf("WRONG ANSWER\n" + "correct: %s %s\n" + "your answer: %s %s\n", correctUsername, correctDob, user.getUsername(), user.getBirthday());
        } catch (NoSuchElementException e) {
            System.out.println("User ID Not found!\n");
        }
    }

    private String solveUsername(String username) {
        String[] words = username.split("\\s");
        StringBuilder processedData = new StringBuilder();

        for (String word : words) {
            processedData.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
        }
        return processedData.toString().trim();
    }

    private String solveDob(String dob) {
        String[] dobParts = dob.split("/");
        return dobParts[2] + "/" + dobParts[0] + "/" + dobParts[1];
    }
}