package rs.ac.bg.fon.nprog.thread;

import rs.ac.bg.fon.nprog.controller.ServerController; 
import rs.ac.bg.fon.nprog.domain.Administrator;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Termin;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import rs.ac.bg.fon.nprog.transfer.Request;
import rs.ac.bg.fon.nprog.transfer.Response;
import rs.ac.bg.fon.nprog.transfer.util.ResponseStatus;
import rs.ac.bg.fon.nprog.transfer.util.Operation;

public class ThreadClient  extends Thread{

	private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                
                Response response = handleRequest(request);
                
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_KORISNIK:
                    ServerController.getInstance().addKorisnik((Korisnik) request.getData());
                    break;
                case Operation.ADD_TERMIN:
                    ServerController.getInstance().addTermin((Termin) request.getData());
                    break;
                case Operation.DELETE_KORISNIK:
                    ServerController.getInstance().deleteKorisnik((Korisnik) request.getData());
                    break;
                case Operation.DELETE_TERMIN:
                    ServerController.getInstance().deleteTermin((Termin) request.getData());
                    break;
                case Operation.UPDATE_KORISNIK:
                    ServerController.getInstance().updateKorisnik((Korisnik) request.getData());
                    break;
                case Operation.UPDATE_TERMIN:
                    ServerController.getInstance().updateTermin((Termin) request.getData());
                    break;
                case Operation.GET_ALL_IGRAC:
                    response.setData(ServerController.getInstance().getAllIgrac((Termin) request.getData()));
                    break;
                case Operation.GET_ALL_KORISNIK:
                    response.setData(ServerController.getInstance().getAllKorisnik());
                    break;
                case Operation.GET_ALL_OPSTINA:
                    response.setData(ServerController.getInstance().getAllOpstina());
                    break;
                case Operation.GET_ALL_TEREN:
                    response.setData(ServerController.getInstance().getAllTeren());
                    break;
                case Operation.GET_ALL_TERMIN:
                    response.setData(ServerController.getInstance().getAllTermin((Korisnik) request.getData()));
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator admin = ServerController.getInstance().login(administrator);
                    response.setData(admin);
                    break;
                case Operation.LOGOUT:
                    Administrator ulogovani = (Administrator) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                case Operation.GET_ALL_GRAD:
                    response.setData(ServerController.getInstance().getAllGrad());
                    break;
                case Operation.GET_ALL_TIP:
                    response.setData(ServerController.getInstance().getAllTip());
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }
	
}
