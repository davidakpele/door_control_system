# Door Control System

A Java implementation of a smart door control system that processes remote control commands and obstacle detection events to determine the door's position over time.

## Overview

This project simulates the behavior of an automated door system with the following characteristics:
- Starts in a closed position (position 0)
- Controlled by a single-button remote
- Features obstacle detection for safety
- Provides real-time position tracking

## Door Behavior Specifications

### Basic Operations
- **Closed door**: Button press starts opening
- **Open door**: Button press starts closing
- **Movement time**: 5 seconds to fully open or close
- **Pause/resume**: Button press pauses movement, another press resumes in same direction
- **Obstacle detection**: Immediately reverses direction when obstacle detected

### Input Format
The input is a string where each character represents one second:
- `.` - No event
- `P` - Button pressed
- `O` - Obstacle detected (takes precedence over 'P')

### Output Format
The output is a string where each character represents the door position at that second:
- `0` - Fully closed
- `1`-`4` - Intermediate positions  
- `5` - Fully open

## Example

**Input**: `..P...O.....`

**Output**: `001234321000`

**Explanation**:
- Seconds 0-1: Door closed (position 0)
- Second 2: Button pressed, starts opening
- Seconds 3-5: Opening continues (positions 1-3)
- Second 6: Obstacle detected, reverses to closing
- Seconds 7-10: Closing continues (positions 2-0)
- Seconds 11+: Door closed (position 0)

## Implementation

The main logic is implemented in the `Challenge` class with the `consumeDoorEvents` method:

```java
public static String consumeDoorEvents(String events)
```

### Key Components
- **Position tracking**: Integer from 0 (closed) to 5 (open)
- **State management**: 
  - `isMoving`: Whether door is currently moving
  - `isOpening`: Current direction (true = opening, false = closing)
  - `isPaused`: Whether movement is paused

### Algorithm
1. Process each event in sequence
2. Handle obstacles by reversing direction immediately
3. Manage button presses to start/stop/pause movement
4. Update position each second based on current state
5. Automatically stop when fully open or closed

## Usage

```java
String input = "..P...O.....";
String output = Challenge.consumeDoorEvents(input);
System.out.println(output); // Output: 001234321000
```

## Requirements

- Java 8 or higher
- No external dependencies

## Project Structure

```
├── Challenge.java    # Main implementation class
└── README.md        # This documentation
```

## Testing

The main method includes a basic test case. For comprehensive testing, consider adding unit tests with various scenarios:
- Simple open/close sequences
- Multiple button presses
- Obstacle detection during movement
- Edge cases (rapid presses, obstacles at boundaries)