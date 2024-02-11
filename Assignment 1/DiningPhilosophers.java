public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {
    // *np* is the number of philosophers (and forks).
    int np = Integer.parseInt(args[0]);
    // *nc* is the number of cycles that each philosopher will go through. If this parameter is 0, they run forever.
    int nc = Integer.parseInt(args[1]);
    // *tt* is the maximum thinking time, in units of milliseconds.
    int tt = Integer.parseInt(args[2]);
    // *et* is the maximum eating time, in units of milliseconds.
    int et = Integer.parseInt(args[3]);
    // If *rl* is 0, then all philosophers are right-handed and try to grab their right chopstick first. If *rl* is 1,
    // then even-numbered philosophers are right-handed and odd-numbered philosophers are  left-handed.
    int rl = Integer.parseInt(args[4]);

    Philosopher[] philosophers = new Philosopher[np];
    Chopstick[] chopsticks = new Chopstick[np];
    
    for (int i = 0; i < np; ++i)
      chopsticks[i] = new Chopstick(i);
    for (int i = 0; i < np; ++i) {
      // The right side will be 2 when the id is even (right-handed) and 1 when the id is odd (left-handed)
      // This means that if rl is 0, they will always be right-handed, but if rl is 1, then they will only be
      // right-handed when the id is even, and left-handed when it is odd.
      philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % np], nc, tt, et, rl < ((i+1)%2)+1);
      philosophers[i].start();
    }
    for (int i = 0; i < np; ++i)
      philosophers[i].join();
  }
}
