package net.minecraft.server.dedicated;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import net.minecraft.server.management.ServerConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DedicatedPlayerList extends ServerConfigurationManager
{
    private static final Logger LOGGER = LogManager.getLogger();

    public DedicatedPlayerList(DedicatedServer server)
    {
        super(server);
        this.setViewDistance(server.getIntProperty("view-distance", 10));
        this.maxPlayers = server.getIntProperty("max-players", 20);
        this.setWhiteListEnabled(server.getBooleanProperty("white-list", false));

        if (!server.isSinglePlayer())
        {
            this.getBannedPlayers().setLanServer(true);
            this.getBannedIPs().setLanServer(true);
        }

        this.loadUserBansList();
        this.saveUserBanList();
        this.loadIpBanList();
        this.saveIpBanList();
        this.loadOpsList();
        this.readWhiteList();
        this.saveOpsList();

        if (!this.getWhitelistedPlayers().getSaveFile().exists())
        {
            this.saveWhiteList();
        }
    }

    public void setWhiteListEnabled(boolean whitelistEnabled)
    {
        super.setWhiteListEnabled(whitelistEnabled);
        this.getServerInstance().setProperty("white-list", Boolean.valueOf(whitelistEnabled));
        this.getServerInstance().saveProperties();
    }

    public void addOp(GameProfile profile)
    {
        super.addOp(profile);
        this.saveOpsList();
    }

    public void removeOp(GameProfile profile)
    {
        super.removeOp(profile);
        this.saveOpsList();
    }

    public void removePlayerFromWhitelist(GameProfile profile)
    {
        super.removePlayerFromWhitelist(profile);
        this.saveWhiteList();
    }

    public void addWhitelistedPlayer(GameProfile profile)
    {
        super.addWhitelistedPlayer(profile);
        this.saveWhiteList();
    }

    /**
     * Either does nothing, or calls readWhiteList.
     */
    public void loadWhiteList()
    {
        this.readWhiteList();
    }

    private void saveIpBanList()
    {
        try
        {
            this.getBannedIPs().writeChanges();
        }
        catch (IOException ioexception)
        {
            LOGGER.warn((String)"Failed to save ip banlist: ", (Throwable)ioexception);
        }
    }

    private void saveUserBanList()
    {
        try
        {
            this.getBannedPlayers().writeChanges();
        }
        catch (IOException ioexception)
        {
            LOGGER.warn((String)"Failed to save user banlist: ", (Throwable)ioexception);
        }
    }

    private void loadIpBanList()
    {
        try
        {
            this.getBannedIPs().readSavedFile();
        }
        catch (IOException ioexception)
        {
            LOGGER.warn((String)"Failed to load ip banlist: ", (Throwable)ioexception);
        }
    }

    private void loadUserBansList()
    {
        try
        {
            this.getBannedPlayers().readSavedFile();
        }
        catch (IOException ioexception)
        {
            LOGGER.warn((String)"Failed to load user banlist: ", (Throwable)ioexception);
        }
    }

    private void loadOpsList()
    {
        try
        {
            this.getOppedPlayers().readSavedFile();
        }
        catch (Exception exception)
        {
            LOGGER.warn((String)"Failed to load operators list: ", (Throwable)exception);
        }
    }

    private void saveOpsList()
    {
        try
        {
            this.getOppedPlayers().writeChanges();
        }
        catch (Exception exception)
        {
            LOGGER.warn((String)"Failed to save operators list: ", (Throwable)exception);
        }
    }

    private void readWhiteList()
    {
        try
        {
            this.getWhitelistedPlayers().readSavedFile();
        }
        catch (Exception exception)
        {
            LOGGER.warn((String)"Failed to load white-list: ", (Throwable)exception);
        }
    }

    private void saveWhiteList()
    {
        try
        {
            this.getWhitelistedPlayers().writeChanges();
        }
        catch (Exception exception)
        {
            LOGGER.warn((String)"Failed to save white-list: ", (Throwable)exception);
        }
    }

    public boolean canJoin(GameProfile profile)
    {
        return !this.isWhiteListEnabled() || this.canSendCommands(profile) || this.getWhitelistedPlayers().isWhitelisted(profile);
    }

    public DedicatedServer getServerInstance()
    {
        return (DedicatedServer)super.getServerInstance();
    }

    public boolean bypassesPlayerLimit(GameProfile p_183023_1_)
    {
        return this.getOppedPlayers().bypassesPlayerLimit(p_183023_1_);
    }
}
