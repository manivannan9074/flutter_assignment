import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class SimpleReminderApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Select day
        System.out.println("Select Day of the Week:");
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ": " + days[i]);
        }
        int dayIndex = scanner.nextInt() - 1;

        // Select time
        System.out.println("Enter time (HH:MM):");
        String timeInput = scanner.next();
        LocalTime selectedTime = LocalTime.parse(timeInput);

        // Select activity
        System.out.println("Select Activity:");
        String[] activities = {
                "Wake up", "Go to gym", "Breakfast", "Meetings",
                "Lunch", "Quick nap", "Go to library", "Dinner", "Go to sleep"
        };
        for (int i = 0; i < activities.length; i++) {
            System.out.println((i + 1) + ": " + activities[i]);
        }
        int activityIndex = scanner.nextInt() - 1;

        // Calculate delay
        long delay = calculateDelay(selectedTime);
        System.out.printf("Reminder set for %s at %s: %s%n", days[dayIndex], selectedTime, activities[activityIndex]);

        // Schedule reminder
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Reminder: " + activities[activityIndex]);
            }
        }, delay);

        scanner.close();
    }

    private static long calculateDelay(LocalTime selectedTime) {
        LocalTime now = LocalTime.now();
        long delay;
        if (selectedTime.isAfter(now)) {
            delay = selectedTime.toSecondOfDay() - now.toSecondOfDay();
        } else {
            delay = TimeUnit.HOURS.toSeconds(24) - now.toSecondOfDay() + selectedTime.toSecondOfDay();
        }
        return delay * 1000; // Convert seconds to milliseconds
    }
}