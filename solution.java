class Solution {
    public static String consumeDoorEvents(String events) {
        if (events == null || events.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int position = 0;         // 0 = closed, 5 = fully open
        boolean isMoving = false;   // whether door is currently moving
        boolean isOpening = true;   // direction: true=open, false=close
        boolean isPaused = false;   // paused state

        for (int i = 0; i < events.length(); i++) {
            char event = events.charAt(i);

            if (event == 'O') {
                // Obstacle: reverse direction immediately
                if (isMoving && !isPaused) {
                    isOpening = !isOpening;
                }
                // If door was stopped, obstacle has no effect
            } else if (event == 'P') {
                if (!isMoving) {
                    // Start moving
                    isMoving = true;
                    isOpening = (position == 0); // if closed, open; if open, close
                    isPaused = false;
                } else {
                    // Toggle pause/resume
                    isPaused = !isPaused;
                }
            }

            // Update position immediately if moving and not paused
            if (isMoving && !isPaused) {
                if (isOpening) {
                    position = Math.min(5, position + 1);
                } else {
                    position = Math.max(0, position - 1);
                }

                // Stop automatically if fully open or closed
                if (position == 0 || position == 5) {
                    isMoving = false;
                    isPaused = false;
                }
            }

            // Append current position for this second
            result.append(position);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "..P...O.....";
        String output = consumeDoorEvents(input);
        System.out.println(output); // Expected: 001234321000
    }
}