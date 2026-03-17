package session08.homework04;

interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

