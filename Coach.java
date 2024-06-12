import java.util.ArrayList;
import java.util.List;

public class Coach 
{
    private String name;
    private List<Review> reviews;

    // Constructor
    public Coach(String name) 
    {
        this.name = name;
        this.reviews = new ArrayList<>();
    }

    // Getter for name
    public String getName() 
    {
        return name;
    }

    // Setter for name
    public void setName(String name) 
    {
        this.name = name;
    }

    // Getter for reviews
    public List<Review> getReviews() 
    {
        return reviews;
    }

    // Add a review
    public void addReview(Review review) 
    {
        if (review != null) {
            reviews.add(review);
        }
    }

    // Method to calculate average rating
    public double calculateAverageRating() 
    {
            if (reviews.isEmpty()) 
        {
            return 0.0; // Return 0 if there are no reviews
        }

        double total = 0;
        for (Review review : reviews) 
        {
            total += review.getRating();
        }
        return total / reviews.size();
    }
}
