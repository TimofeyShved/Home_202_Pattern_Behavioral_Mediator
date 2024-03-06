package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    // Паттерн (Медиатор) - он работает как чекбокс
        // включает нужный класс, а другие отключает
        // помогает в общении между объектами
        // например сообщение в вк, пока не псомтортели сообщение, он false
        // но стоит зайти человеку то теперь сообщение true

        // конкретный медиатор
        ConcretMeiator meiator = new ConcretMeiator();

        // создадим ему коллег
        meiator.add(new ConcretCollega(meiator, "One"));
        meiator.add(new ConcretCollega(meiator, "Two"));


        ConcretCollega c = new ConcretCollega(meiator, "Five");
        meiator.add(c);

        // конкретный коллега получил статус
        //остольные в этот момент false
        c.setStatus();
    }
}

// создадим медиатор
interface Mediator{
    void requestAll(Collega collega);
}

class ConcretMeiator implements Mediator{
    List<Collega> collegues = new ArrayList<>();

    void add (Collega collega){
        collegues.add(collega);
    }

    @Override
    public void requestAll(Collega collega) {
        // при обновлении заставляет поменять статус

        collega.setTrue();

        for (Collega c: collegues){
            if (c != collega){
                c.setFalse();
            }
        }

    }
}


// создадим коллег
interface Collega{
    void setTrue();
    void setFalse();
    void setStatus();
}

class ConcretCollega implements Collega{
    boolean status;
    Mediator mediator;
    String name;

    public ConcretCollega(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    @Override
    public void setTrue() {
        status = true;
        System.out.println(name + " status is true");
    }

    @Override
    public void setFalse() {
        status = false;
        System.out.println(name + " status is false");
    }

    @Override
    public void setStatus() {
        mediator.requestAll(this);
    }
}
