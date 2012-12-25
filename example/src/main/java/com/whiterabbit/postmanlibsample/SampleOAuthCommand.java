package com.whiterabbit.postmanlibsample;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.whiterabbit.postman.commands.OAuthServerCommand;
import com.whiterabbit.postman.commands.ResultParseException;
import org.scribe.model.Verb;

/**
 * Created with IntelliJ IDEA.
 * User: fedepaol
 * Date: 12/26/12
 * Time: 12:33 AM
 */
public class SampleOAuthCommand extends OAuthServerCommand{
    @Override
    protected Verb getVerb() {
        return null;  //TODO Autogenerated
    }

    @Override
    protected String getOAuthServiceName() {
        return "Twitter";   // TODO
    }

    @Override
    protected String getUrl(Verb v) {
        return "https://api.twitter.com/1/statuses/update.json";
    }

    @Override
    protected void processHttpResult(String result, Context context) throws ResultParseException {
        //TODO Autogenerated
    }

    @Override
    protected String[] getParams() {
        return new String[0];  //TODO Autogenerated
    }

    @Override
    protected String[] getValues() {
        return new String[0];  //TODO Autogenerated
    }

    @Override
    public int describeContents() {
        return 0;  //TODO Autogenerated
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //TODO Autogenerated
    }



    public static final Parcelable.Creator<SampleOAuthCommand> CREATOR
            = new Parcelable.Creator<SampleOAuthCommand>() {
        public SampleOAuthCommand createFromParcel(Parcel in) {
            return new SampleOAuthCommand(in);
        }

        public SampleOAuthCommand[] newArray(int size) {
            return new SampleOAuthCommand[size];
        }
    };


    private SampleOAuthCommand(Parcel in){

    }

    public SampleOAuthCommand(){

    }
}