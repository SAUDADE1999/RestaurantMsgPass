package serverSide.main;

import commInfra.ServerCom;
import genclass.GenericIO;
import serverSide.entities.ServiceProviderAgent;
import serverSide.sharedRegions.SharedRegionInterface;
import serverSide.sharedRegions.Table;
import serverSide.sharedRegions.TableInterface;
import serverSide.stubs.GeneralReposStub;

import java.net.SocketTimeoutException;

public class TableMain {
    /**
     *    Main method.
     *
     *    @param args runtime arguments
     */
    public static void main (String[] args)
    {
        /* service is established */

        ServerCom serverCom, sconi;                                        // communication channels
        int portNumb = SimulPar.TABLE_PORT;                                          // port nunber for listening to service requests

        serverCom = new ServerCom (portNumb);
        serverCom.start ();                             // service is instantiated
        serverCom.setSoTimeout(10000);
        GenericIO.writelnString ("Service is established!");
        GenericIO.writelnString ("Server is listening for service requests.");

        GeneralReposStub generalReposStub = new GeneralReposStub(SimulPar.GENERALREPOS_HOSTNAME, SimulPar.GENERALREPOS_PORT);
        Table table = new Table(generalReposStub);
        SharedRegionInterface sharedRegionInterface = new TableInterface(table);

        /* service request processing */
        // service provider agent
        while (!sharedRegionInterface.hasShutdown())
        {
            try {
                sconi = serverCom.accept ();                                     // enter listening procedure
                ServiceProviderAgent serviceProviderAgent = new ServiceProviderAgent (sconi, sharedRegionInterface);            // start a service provider agent to address
                serviceProviderAgent.start ();
            }
            catch(SocketTimeoutException ste) {}
        }

        GenericIO.writelnString ("Service is closed!");
    }
}
