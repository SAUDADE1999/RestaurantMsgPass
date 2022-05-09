package clientSide.stubs;

import clientSide.entities.Waiter;
import commInfra.CommunicationChannel;
import commInfra.Message;

public class BarStub implements IBar_Waiter {

    String serverHostName;

    int serverPortNumb;

    public BarStub(String serverName, int port) {
        serverHostName = serverName;
        serverPortNumb = port;
    }


    @Override
    public void sayGoodbye() {
        Waiter waiter = (Waiter) Thread.currentThread();
        CommunicationChannel com = new CommunicationChannel(serverHostName, serverPortNumb);
        Object[] params = new Object[0];
        Object[] state_fields = new Object[1];
        state_fields[0] =waiter.getWaiterId();
        state_fields[1] = waiter.getWaiterState();

        /* operation number to be defined */
        Message m_toServer = new Message(1, params, 0, state_fields, 2, null);
        Message m_fromServer;

        while (!com.open ())
        { try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
        }

        com.writeObject (m_toServer);

        m_fromServer = (Message) com.readObject();

        waiter.setWaiterState((Integer) m_fromServer.getStateFields()[1]);
        //boolean result = (Boolean) m_fromServer.getReturnValue();

        com.close ();
    }

    @Override
    public void prepareTheBill() {
        Waiter waiter = (Waiter) Thread.currentThread();
        CommunicationChannel com = new CommunicationChannel(serverHostName, serverPortNumb);
        Object[] params = new Object[0];
        Object[] state_fields = new Object[1];
        state_fields[0] =waiter.getWaiterId();
        state_fields[1] = waiter.getWaiterState();

        /* operation number to be defined */
        Message m_toServer = new Message(2, params, 0, state_fields, 2, null);
        Message m_fromServer;

        while (!com.open ())
        { try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
        }

        com.writeObject (m_toServer);

        m_fromServer = (Message) com.readObject();

        waiter.setWaiterState((Integer) m_fromServer.getStateFields()[1]);
        //boolean result = (Boolean) m_fromServer.getReturnValue();

        com.close ();
    }
}