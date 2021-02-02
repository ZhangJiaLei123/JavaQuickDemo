package test.model.ftp;

import blxt.qjava.autovalue.inter.OnFtpServerListener;
import org.apache.ftpserver.DataConnectionConfiguration;
import org.apache.ftpserver.impl.FtpIoSession;
import org.apache.ftpserver.impl.FtpServerContext;
import org.apache.ftpserver.ipfilter.SessionFilter;
import org.apache.ftpserver.ssl.SslConfiguration;
import org.apache.mina.filter.firewall.Subnet;

import java.net.InetAddress;
import java.util.List;
import java.util.Set;

@OnFtpServerListener
public class FtpServerListener implements org.apache.ftpserver.listener.Listener{

    @Override
    public void start(FtpServerContext ftpServerContext) {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStopped() {
        return false;
    }

    @Override
    public void suspend() {

    }

    @Override
    public void resume() {

    }

    @Override
    public boolean isSuspended() {
        return false;
    }

    @Override
    public Set<FtpIoSession> getActiveSessions() {
        return null;
    }

    @Override
    public boolean isImplicitSsl() {
        return false;
    }

    @Override
    public SslConfiguration getSslConfiguration() {
        return null;
    }

    @Override
    public int getPort() {
        return 8082;
    }

    @Override
    public String getServerAddress() {
        return null;
    }

    @Override
    public DataConnectionConfiguration getDataConnectionConfiguration() {
        return null;
    }

    @Override
    public int getIdleTimeout() {
        return 0;
    }

    @Override
    public List<InetAddress> getBlockedAddresses() {
        return null;
    }

    @Override
    public List<Subnet> getBlockedSubnets() {
        return null;
    }

    @Override
    public SessionFilter getSessionFilter() {
        return null;
    }
}
