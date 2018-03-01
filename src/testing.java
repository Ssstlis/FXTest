
import Fox.core.lib.general.data.ID3V2;
import Fox.core.lib.general.utils.ExecutableHelper;
import Fox.core.main.SearchLib;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import static Fox.core.lib.general.utils.performance.CLOSETOMAX;


public class testing
{

    private static String ID3V2toString(ID3V2 item)
    {
        String result = "";
        if (item != null)
        {
            if (item.hasTrackMBID())
                result += "TrackMBID : " + item.getTrackMBID() + "\n";
            if (item.hasTitle())
                result += "Title : " + item.getTitle() + "\n";
            if (item.hasArtistMBID())
                result += "ArtistMBID : " + item.getArtistMBID() + "\n";
            if (item.hasArtist())
                result += "Artist : " + item.getArtist() + "\n";
            if (item.hasAlbumMBID())
                result += "AlbumMBID : " + item.getAlbumMBID() + "\n";
            if (item.hasAlbum())
                result += "Album : " + item.getAlbum() + "\n";
            if (item.hasTag())
                result += "Tag : " + item.getTag() + "\n";
            if (item.hasNumber())
                result += "Number : " + item.getNumber() + "\n";
            if (item.hasYear())
                result += "Year : " + item.getYear() + "\n";
            if (item.hasComment())
                result += "Comment : " + item.getComment() + "\n";
            if (item.hasArtLinks())
                for(String link : item.getArtLinks())
                    result = result.concat(link + "\n");
        }
        return result;
    }

    public static void main(String args,
                            final JProgressBar bar0,
                            final JProgressBar bar1,
                            final JProgressBar bar2,
                            final JProgressBar bar3,
                            final JProgressBar bar4)
    {
        java.util.logging.Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);
        Logger logger = LoggerFactory.getLogger(SearchLib.class);
        try
        {
            System.err.println(args);

            List<File> FileList = ExecutableHelper.GetFileList(args, new MediaFilter(), new CustomProgressState(bar0, 1, "Finder", "Finder"));

            int size = FileList.size();
            if (size == 0)
                return ;

            for (Map.Entry<Object, Object> item : System.getProperties().entrySet())
                System.out.println(item.getKey() + " : " + item.getValue());

            for (Map.Entry<String, String> item : System.getenv().entrySet())
                System.out.println(item.getKey() + " : " + item.getValue());

            long temp = System.currentTimeMillis();
            logger.info("Start {}", temp);

            List<ID3V2> res = null;
            Entry<Map<String, List<ID3V2>>, List<String>> Result = null;
            if (size == 1)
                res = SearchLib.SearchTags(ExecutableHelper.FilesToStrings(FileList).get(0),
                                           new WindowsFPcalc(),
                                           new CustomProgressState(bar4,4, "Common", "Common"),
                                           5);
            else
            Result = SearchLib.SearchTags(ExecutableHelper.FilesToStrings(FileList),
                                          new WindowsFPcalc(),
                                          new CustomProgressState(bar1, size, "Checker", "Checker"),
                                          new CustomProgressState(bar2, size, "FingerPrint", "FingerPrint"),
                                          new CustomProgressState(bar3, size, "Service", "Service"),
                                          new CustomProgressState(bar4,size*3, "Common", "Common"),
                                          CLOSETOMAX, 5);

            temp = System.currentTimeMillis() - temp;
            if (logger.isInfoEnabled())
                logger.info("End {} \n Overall assessment of the work {} \n", temp, Result !=null && Result.getKey().size() == size ? "good" : "bad");

            if (logger.isInfoEnabled() && Result != null && Result.getKey().size() != 0)
            {
                for (Entry<String, List<ID3V2>> item : Result.getKey().entrySet())
                {
                    logger.info("File {} Results:", item.getKey());
                    for (ID3V2 elem : item.getValue())
                        logger.info("\n{}", ID3V2toString(elem));
                }
                List<String> value = Result.getValue();
                if (value != null && !value.isEmpty())
                {
                    logger.info("Rejected :");
                    for (String item : value)
                        logger.info("{}", item);
                }
            }
            if (logger.isInfoEnabled() && res != null && !res.isEmpty())
            {
                logger.info("File {} Results:", ExecutableHelper.FilesToStrings(FileList).get(0));
                for (ID3V2 elem : res)
                    logger.info("{}", ID3V2toString(elem));
            }
        }
        catch (Exception e)
        {
            if (logger.isErrorEnabled())
                logger.error("", e);
        }
    }
}
