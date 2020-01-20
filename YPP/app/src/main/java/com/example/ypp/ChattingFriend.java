package com.example.ypp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaledrone.lib.HistoryRoomListener;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;
import com.scaledrone.lib.SubscribeOptions;

import java.util.Random;

public class ChattingFriend extends AppCompatActivity implements RoomListener {

    private String channelID = "eHPeAJfA4912yISd";
    private String roomName = "observable-friends";
    private EditText editText;
    private Scaledrone scaledrone;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private TextView textView;
    private ImageButton back;
    private Room room;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_layout_2);

        editText = (EditText) findViewById(R.id.editText);
        textView = findViewById(R.id.channelTitle);
        back = findViewById(R.id.chattingquit);

        messageAdapter = new MessageAdapter(ChattingFriend.this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);


        final String channel = getChannelName();
        if (channel != null) {
            textView.setText(channel);
        }

//        MemberData data = new MemberData(getRandomName(), getRandomColor());
        MemberData data = new MemberData("Cherry", "#ff00ff");

        scaledrone = new Scaledrone(channelID, data);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                System.out.println("Scaledrone connection open");
//                scaledrone.subscribe(roomName, ChattingFriend.this);
                room = scaledrone.subscribe(roomName, new RoomListener() {

                    @Override
                    public void onOpen(Room room) {
                    }

                    @Override
                    public void onOpenFailure(Room room, Exception ex) {
                        // This can happen when you don't have correct permissions
                        System.out.println("Failed to subscribe to room: " + ex.getMessage());
                    }

                    @Override
                    public void onMessage(Room room, com.scaledrone.lib.Message receivedMessage) {

                        ObjectMapper mapper = new ObjectMapper();
                        System.out.println("Message: " + receivedMessage.getData().asText());

//                        try{
//                            MemberData member1 = new MemberData("Lee", "#ff00ff");
//                            if (receivedMessage.getMember().getClientData() == null || receivedMessage.getClientID() == null) {
                                if (message != null && message.equals(receivedMessage.getData().asText())) {
                                    MemberData data = new MemberData("Cherry", "#ff00ff");
                                    final Message message = new Message(receivedMessage.getData().asText(), data, true);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            messageAdapter.add(message);
                                            messagesView.setSelection(messagesView.getCount() - 1);
                                        }
                                    });
                                } else {
                                    final String name = channel;
                                    MemberData data = new MemberData(name, "#ff00ff");
                                    final Message message = new Message(receivedMessage.getData().asText(), data, false);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            messageAdapter.add(message);
                                            messagesView.setSelection(messagesView.getCount() - 1);
                                        }
                                    });
                                }
//                            } else {
//                                MemberData data = mapper.treeToValue(receivedMessage.getMember().getClientData(), MemberData.class);
//                                boolean belongsToCurrentUser = receivedMessage.getClientID().equals(scaledrone.getClientID());
//                                final Message message = new Message(receivedMessage.getData().asText(), data, belongsToCurrentUser);
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        messageAdapter.add(message);
//                                        messagesView.setSelection(messagesView.getCount() - 1);
//                                    }
//                                });
//                            }
//                        } catch (JsonProcessingException e) {
//                            e.printStackTrace();
//                        }
                    }
                });

            }

            @Override
            public void onOpenFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onClosed(String reason) {
                System.err.println(reason);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChattingFriend.this, MainPage.class);
                startActivity(intent);
            }
        });

    }


    private String getChannelName() {
        String channel = getIntent().getStringExtra("SELECTED_CHANNEL");
        return channel;
    }

    // Successfully connected to Scaledrone room
    @Override
    public void onOpen(Room room) {
        System.out.println("Conneted to room");
    }

    // Connecting to Scaledrone room failed
    @Override
    public void onOpenFailure(Room room, Exception ex) {
        System.err.println(ex);
    }

    @Override
    public void onMessage(Room room, com.scaledrone.lib.Message receivedMessage) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final MemberData data = mapper.treeToValue(receivedMessage.getMember().getClientData(), MemberData.class);
            System.out.println("data -> " + data.toString() + "    " + data.getName());

            boolean belongsToCurrentUser = receivedMessage.getClientID().equals(scaledrone.getClientID());

            System.out.println("boolean -> " + belongsToCurrentUser);
            System.out.println("boolean -> " + scaledrone.getClientID());
            final Message message = new Message(receivedMessage.getData().asText(), data, belongsToCurrentUser);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(message);
                    messagesView.setSelection(messagesView.getCount() - 1);
                }
            });
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
    }

    private String getRandomName() {
        String[] adjs = {"autumn", "hidden", "bitter", "misty", "silent", "empty", "dry", "dark", "summer", "icy", "delicate", "quiet", "white", "cool", "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy", "weathered", "blue", "billowing", "broken", "cold", "damp", "falling", "frosty", "green", "long", "late", "lingering", "bold", "little", "morning", "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing", "shy", "wandering", "withered", "wild", "black", "young", "holy", "solitary", "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine", "polished", "ancient", "purple", "lively", "nameless"};
        String[] nouns = {"waterfall", "river", "breeze", "moon", "rain", "wind", "sea", "morning", "snow", "lake", "sunset", "pine", "shadow", "leaf", "dawn", "glitter", "forest", "hill", "cloud", "meadow", "sun", "glade", "bird", "brook", "butterfly", "bush", "dew", "dust", "field", "fire", "flower", "firefly", "feather", "grass", "haze", "mountain", "night", "pond", "darkness", "snowflake", "silence", "sound", "sky", "shape", "surf", "thunder", "violet", "water", "wildflower", "wave", "water", "resonance", "sun", "wood", "dream", "cherry", "tree", "fog", "frost", "voice", "paper", "frog", "smoke", "star"};
        return (
                adjs[(int) Math.floor(Math.random() * adjs.length)] +
                        "_" +
                        nouns[(int) Math.floor(Math.random() * nouns.length)]
        );
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    public void sendMessage(View view) {
        message = editText.getText().toString();
        if (message.length() > 0) {
//            scaledrone.publish(roomName, message);
            room.publish(message);
            editText.getText().clear();
        }
    }

}

