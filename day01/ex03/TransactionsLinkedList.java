package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode first = null;
    private TransactionNode last = null;
    private Integer         length = 0;

    public void printTransactionsList() {
        TransactionNode tmp = this.first;
        if (tmp == null) {
            System.out.println("Transactions list is empty");
        } else {
            System.out.println("Found " + this.length + " transactions:");
        }
        while (tmp != null) {
            tmp.getCurrent().printTransactionInfo();
            tmp = tmp.getNext();
        }
    }

    public Integer getLength() {
        return length;
    }

    @Override
    public void addTransaction(Transaction instance) {
        if (this.first == null) {
            this.first = new TransactionNode(instance, null, null);
            this.last = this.first;
        } else {
            TransactionNode tmp = new TransactionNode(instance,null, this.last);
            this.last.setNext(tmp);
            this.last = tmp;
        }
        this.length++;
    }

    @Override
    public void removeTransactionById(UUID id) {
        TransactionNode tmp = this.first;
        while (tmp != null) {
            if (tmp.getCurrent().getId() == id) {
                if (tmp == this.first) {
                    if (tmp.getNext() == null) {
                        this.last = this.first = null;
                    } else {
                        tmp.getNext().setPrev(tmp.getPrev());
                        this.first = tmp.getNext();
                    }
                } else if (tmp == this.last) {
                    tmp.getPrev().setNext(tmp.getNext());
                    this.last = tmp.getPrev();
                } else {
                    tmp.getNext().setPrev(tmp.getPrev());
                    tmp.getPrev().setNext(tmp.getNext());
                }
                this.length--;
                return;
            }
            tmp = tmp.getNext();
        }
        throw new TransactionNotFoundException("Error: Transaction with ID: " + id + " not found.");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] res = new Transaction[this.length];
        TransactionNode iter = this.first;
        for (int i = 0; i < this.length; i++) {
            res[i] = iter.getCurrent();
            iter = iter.getNext();
        }
        return res;
    }
}

class TransactionNode {
    private Transaction         current;
    private TransactionNode     next;
    private TransactionNode     prev;

    public TransactionNode(Transaction current, TransactionNode next, TransactionNode prev) {
        this.current = current;
        this.next = next;
        this.prev = prev;
    }

    public Transaction getCurrent() {
        return current;
    }

    public TransactionNode getNext() {
        return next;
    }

    public TransactionNode getPrev() {
        return prev;
    }

    public void setCurrent(Transaction current) {
        this.current = current;
    }

    public void setNext(TransactionNode next) {
        this.next = next;
    }

    public void setPrev(TransactionNode prev) {
        this.prev = prev;
    }
}
