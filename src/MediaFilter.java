
import Fox.core.main.SearchLib;
import org.jaudiotagger.audio.AudioFileIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;

public class MediaFilter
        implements FileFilter
{
    @Override
    public boolean accept(File pathname)
    {
        Logger logger = LoggerFactory.getLogger(SearchLib.class);
        try
        {
            String name = pathname.getName();
            return (name.endsWith(".mp3")
                    || name.endsWith(".flac")
                    || name.endsWith(".ogg")
                    || name.endsWith(".waw")
                    || name.endsWith(".wma"));
        }
        catch (Exception e)
        {
            if (logger.isErrorEnabled())
                logger.error("", e);
            return false;
        }
    }
}

