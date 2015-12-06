package jorgeandcompany.loveletter;

/**
 * Created by Fan on 11/19/2015.
 */
public class Producer implements Runnable{

    private Player player1;
    private volatile int counter = 0;
    private Buffer buffer;

    public Producer(Player player, Buffer buf)
    {
        player1 = player;
        buffer = buf;
    }

    @Override
    public void run() {
        for (int i = 0; counter != 2; i++) {
            try {
                if(counter == 0) {
                    buffer.put(player1.getCard().getValue());
                    player1.setCard(null);
                    player1.setSeven(false);
                    counter++;
                }
                else
                {
                    if (GameData.getDeckCount() == 0) {
                       buffer.put(GameData.drawOutCard().getValue());
                    } else {
                        buffer.put(GameData.deck.draw().getValue());
                        }
                        counter++;
                    }
                } catch (InterruptedException e) {return;}
            try {
                Thread.sleep( (int) Math.random() * 100); // sleep for a randomly chosen time
            } catch (InterruptedException e) {return;}
        }
    }
}
