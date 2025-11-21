class Solution {
    public static String consumeDoorEvents(String events) {
        if (events == null || events.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        // 0 = closed, 5 = fully open
        int position = 0;  
        // whether door is currently moving
        boolean isMoving = false;   
        // direction: true=open, false=close
        boolean isOpening = true; 
        // paused state  
        boolean isPaused = false;   

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
                     // if closed, open; if open, close
                    isOpening = (position == 0);
                    isPaused = false;
                } else {
                    // Toggle pause/resume
                    isPaused = !isPaused;
                }
            }

            // position immediately if moving and not paused
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
        System.out.println(output); 
    }
}
