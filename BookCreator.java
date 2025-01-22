import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter; 
import java.io.FileReader;
import java.io.IOException;

public class BookCreator{
    public static void main(String[] args){
        run();
    }

    public static void run(){
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.print("Would you like to add a new book? (y/n?)");
            String text = scanner.nextLine();
            System.out.println(text);
            if(text.toLowerCase().startsWith("y")){
                addEntry(scanner);
            }
            else{
                System.out.println("Do you want to edit an existing entry? (y/n?)");
                String textAmmend = scanner.nextLine();
                if(textAmmend.toLowerCase().startsWith("y")){
                    editEntry(scanner);
                }
                else{}

                break;
            }
        }

        scanner.close();
    }

    public static void addEntry(Scanner scanner){
        String title = titleInput(scanner);
        String author = authorInput(scanner);
        Double rating = ratingInput(scanner);
        String review = reviewInput(scanner);
        String cover = coverInput(scanner);

        String JSONAddition = convertToJSON(title, author, rating, review, cover);
        
        addToJSONFile(JSONAddition);

        System.out.println("Entry for " + title + " successful!");
    }

    public static void addToJSONFile(String JSONToAdd){
        try {
            Scanner scanner = new Scanner(new FileReader("books.json"));
            StringBuilder jsonContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }
            scanner.close();

            // Parse existing JSON content into a JSONArray
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Parse the new JSON string and add it to the array
            JSONObject newBook = new JSONObject(JSONToAdd);
            jsonArray.put(newBook);

            // Write the updated JSON back to the file
            FileWriter fileWriter = new FileWriter("books.json", false); // Overwrite mode
            fileWriter.write(jsonArray.toString(4)); // Pretty print with indentation
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while handling the file.");
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            System.out.println("An error occurred while parsing JSON.");
            e.printStackTrace();
        }
    }

    public static String convertToJSON(String title, String author, Double rating, String review, String cover){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cover", "Cover-Photos/" + cover);
        jsonObject.put("title", title);
        jsonObject.put("author", author);
        jsonObject.put("rating", rating);
        jsonObject.put("review", review);
        return jsonObject.toString();
    }

    public static String coverInput(Scanner scanner){
        String cover = "";
        
        while(true){
            System.out.println();
            System.out.println("type the name of the image you want for the cover. If you have not added one take a moment and put it in the 'Cover-Photos' folder. Put the name such as 'Cover.jpg', '129492029330239403.png'");
            cover = scanner.nextLine();
            System.out.println(cover + " in Cover-Photos.... is this correct?");
            if(scanner.nextLine().toLowerCase().startsWith("y")){
                break;
            }
        }

        return cover;
    }

    public static String reviewInput(Scanner scanner){
        String review = "";
        
        while(true){
            System.out.println();
            System.out.println("What is your review of the book? (Type or paste your review, do not hit enter until done)");
            review = scanner.nextLine();
            System.out.println(review + ".... is this correct?");
            if(scanner.nextLine().toLowerCase().startsWith("y")){
                break;
            }
        }

        return review;
    }

    public static Double ratingInput(Scanner scanner){
        Double rating = 0.0;
        String ratingText = "";
        
        while(true){
            System.out.println();
            System.out.println("What is your rating of the book? (0-5 stars, IE. 0.5, 4.5, 3 are accepted. 1.4, 6, -1, will cause issues.)");
            ratingText = scanner.nextLine();
            try{
                rating = Double.parseDouble(ratingText);
                if(rating > 5.0 || rating < 0){
                    throw new java.lang.NumberFormatException();
                }
                if((rating % 0.5) != 0){
                    throw new java.lang.NumberFormatException();
                }
            }
            catch(java.lang.NumberFormatException e){
                System.out.println("NOT A VALID NUMBER");
                rating = 0.0;
            }
            
            System.out.println(rating + ".... is this correct?");
            if(scanner.nextLine().toLowerCase().startsWith("y")){
                break;
            }
        }

        return rating;
    }

    public static String titleInput(Scanner scanner){
        String title = "";
        
        while(true){
            System.out.println();
            System.out.println("What is the title of the book?");
            title = scanner.nextLine();
            System.out.println(title + ".... is this correct?");
            if(scanner.nextLine().toLowerCase().startsWith("y")){
                break;
            }
        }

        return title;
    }

    public static String authorInput(Scanner scanner){
        String author = "";
        
        while(true){
            System.out.println();
            System.out.println("Who is the author of the book?");
            author = scanner.nextLine();
            System.out.println(author + ".... is this correct?");
            if(scanner.nextLine().toLowerCase().startsWith("y")){
                break;
            }
        }

        return author;
    }

    public static void editEntry(Scanner scanner){
        System.out.println();
        System.out.println("NOT YET IMPLEMENTED");
    }
}