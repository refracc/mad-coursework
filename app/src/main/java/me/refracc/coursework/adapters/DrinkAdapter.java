package me.refracc.coursework.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

import me.refracc.coursework.R;
import me.refracc.coursework.info.Drink;

@SuppressWarnings({"deprecated", "unused"})
public class DrinkAdapter extends ArrayAdapter<Drink> {

    private Context ctxt;
    private int resource;
    private int lastPosition = -1;


    private static class ViewHolder {
        TextView name;
        TextView abv;
        TextView volume;
        ImageView image;
    }

    public DrinkAdapter(Context c, int resource, List<Drink> drinks) {
        super(c, resource, drinks);
        ctxt = c;
        this.resource = resource;
    }

    @Override
    public View getView(int pos, View v, @NonNull ViewGroup parent) {

        //sets up the image loader library
        setupImageLoader();

        //get the persons information
        String name = getItem(pos).getName();
        String abv = getItem(pos).getAbv();
        String volume = getItem(pos).getVol();
        String url = getItem(pos).getResURL();

        //create the view anim_result for showing the animation
        final View anim_result;

        //ViewHolder object
        ViewHolder viewHolder;


        if(v == null){
            LayoutInflater inflater = LayoutInflater.from(ctxt);
            v = inflater.inflate(this.resource, parent, false);
            viewHolder= new ViewHolder();
            viewHolder.name = v.findViewById(R.id.name_text);
            viewHolder.abv = v.findViewById(R.id.abv_text);
            viewHolder.volume = v.findViewById(R.id.volume_text);
            viewHolder.image = v.findViewById(R.id.image);

            anim_result = v;

            v.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) v.getTag();
            anim_result = v;
        }


        Animation animation = AnimationUtils.loadAnimation(ctxt,
                (pos > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim); // Depends on scroll direction will modify the
        anim_result.startAnimation(animation);
        lastPosition = pos;

        viewHolder.name.setText(name);
        viewHolder.abv.setText(abv);
        viewHolder.volume.setText(volume);

        //create the im object
        ImageLoader im = ImageLoader.getInstance();

        int errorImage = ctxt.getResources().getIdentifier("@drawable/error",null, ctxt.getPackageName());

        //create display options
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).resetViewBeforeLoading(true).showImageForEmptyUri(errorImage).showImageOnFail(errorImage).showImageOnLoading(errorImage).build();

        //download and display image from url
        im.displayImage(url, viewHolder.image, options);

        return v;
    }

    /**
     * Required for setting up the Universal Image loader Library
     */
    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisc(true).cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ctxt).defaultDisplayImageOptions(defaultOptions).memoryCache(new WeakMemoryCache()).discCacheSize(100 * 1024 * 1024).build();
        ImageLoader.getInstance().init(config);
    }

}
