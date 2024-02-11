import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private int id, thinkCycles, maxThinkTime, maxEatTime;
  private boolean isRightHanded;
  private Random random;
  private int thinkCount;

  public Philosopher(int id, Chopstick left, Chopstick right, int thinkCycles, int maxThinkTime, int maxEatTime, boolean isRightHanded) {
    this.id = id; this.left = left; this.right = right; this.thinkCycles = thinkCycles; this.maxThinkTime = maxThinkTime; this.maxEatTime = maxEatTime;
    this.isRightHanded = isRightHanded;
    random = new Random();
  }

  public void run() {
    int eatTime = 0;
    int thinkTime = 0;
    if (thinkCycles == 0) {thinkCycles = -1;} //run forever if thinkCycles == 0
    try {
      for (int i = 0; i != thinkCycles; ++i)
      {
        ++thinkCount;
        if (thinkCount % 5 == 0)
          System.out.println("Philosopher " + id + " has thought " + thinkCount + " times");
        thinkTime = random.nextInt(maxThinkTime);
        System.out.println("Philosopher " + id + " starts thinking for " + thinkTime + "ms");
        Thread.sleep(thinkTime);     // Think for a while
        System.out.println("Philosopher " + id + " has thought for " + thinkTime + "ms");
        if (isRightHanded)
        {
          System.out.println("Philosopher " + id + " wants right chopstick (id ~" + right.getId() + ")");
          synchronized(right) {                    // Grab right chopstick
            System.out.println("Philosopher " + id + " has right chopstick (id +" + right.getId() + ")");
            System.out.println("Philosopher " + id + " wants left chopstick (id ~" + left.getId() + ")");
            synchronized(left) {                 // Grab left chopstick
              System.out.println("Philosopher " + id + " has left chopstick (id +" + left.getId() + ")");
              eatTime = random.nextInt(maxEatTime);
              System.out.println("Philosopher " + id + " is eating for " + eatTime + " ms with chopsticks (id " + right.getId() + " " + left.getId() + ")");
              Thread.sleep(eatTime); // Eat for a while
              System.out.println("Philosopher " + id + " has eaten for " + eatTime + " ms and release chopsticks (id -" + right.getId() + " -" + left.getId() + ")");
            }
          }
        }
        else
        {
          System.out.println("Philosopher " + id + " wants left chopstick (id ~" + left.getId() + ")");
          synchronized(left) {                    // Grab right chopstick
            System.out.println("Philosopher " + id + " has left chopstick (id +" + left.getId() + ")");
            System.out.println("Philosopher " + id + " wants right chopstick (id ~" + right.getId() + ")");
            synchronized(right) {                 // Grab left chopstick
              System.out.println("Philosopher " + id + " has right chopstick (id +" + right.getId() + ")");
              eatTime = random.nextInt(maxEatTime);
              System.out.println("Philosopher " + id + " is eating for " + eatTime + " ms with chopsticks (id " + right.getId() + " " + left.getId() + ")");
              Thread.sleep(eatTime); // Eat for a while
              System.out.println("Philosopher " + id + " has eaten for " + eatTime + " ms, and releases chopsticks (id -" + right.getId() + " -" + left.getId() + ")");
            }
          }
        }
      }
    } catch(InterruptedException e) {}
  }
}
