package jorgeandcompany.loveletter;

/**
 * Created by Fan on 11/19/2015.
 */
public class Consumer implements Runnable{

    private Player player1;
    private volatile int counter = 0;
    private Buffer buffer;
    private CardFactory cardMaker;

    public Consumer(Player player, Buffer buf)
    {
        buffer = buf;
        player1 = player;
        cardMaker = new ConcreteCardFactory();
    }

    @Override
    public void run() {
        for (int i = 0; counter != 2; i++) {
            int value;
            try {
                Thread.sleep( (int) Math.random() * 100); // sleep for a randomly chosen time
            } catch (InterruptedException e) {return;}
            try {
                if(counter == 0)
                {
                    value = buffer.get();
                    GameData.discardPile.addToDiscard(cardMaker.createCard(value));
                    counter++;
                }
                else {
                    value = buffer.get();
                    if (GameData.getDeckCount() == 0) {
                        player1.setCard(cardMaker.createCard(value));
                        if(value == 7)
                        {
                            player1.setSeven(true);
                        }
                    } else {
                        player1.setCard(cardMaker.createCard(value));
                        if(value == 7)
                        {
                            player1.setSeven(true);
                        }
                    }
                    counter++;
                }
            }  catch (InterruptedException e) {return;}
        }
    }
}
