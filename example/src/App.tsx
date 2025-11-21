import {
  View,
  StyleSheet,
  Dimensions,
  Button,
  // FlatList,
  // Text,
} from 'react-native';
import { TrueListView } from 'react-native-true-list';
// import BASIC_TEMPLATE1 from './templates/basic1';
// import VIEWS_TEMPLATE from './templates/views';
import VIEWS_PLUS_TEXT_TEMPLATE from './templates/views_plus_text';
import { useEffect } from 'react';

export default function App() {
  const TEMPLATE = VIEWS_PLUS_TEXT_TEMPLATE;

  useEffect(() => {
    console.log('ravil', 'true list mounted');
    // setTimeout(() => {
    //   for (let i = 0; i < 1e9; i++) {}
    // }, 2000);
  }, []);

  return (
    <View style={styles.container}>
      <View
        style={{
          width: '100%',
          height: 100,
          backgroundColor: 'red',
          position: 'absolute',
          top: 60,
        }}
      >
        <Button
          title="Press Me"
          onPress={() => {
            for (let i = 0; i < 1e9; i++) {}
            // @ts-ignore
            alert('Hello');
          }}
        />
      </View>
      <TrueListView
        style={styles.box}
        items={TEMPLATE.data}
        dataFitter={TEMPLATE.dataFitter}
        itemTemplate={TEMPLATE.template}
      />
      {/* <FlatList
        data={TEMPLATE.data}
        windowSize={3}
        renderItem={({ item }) => (
          <View
            style={{
              backgroundColor: 'tomato',
              padding: 24,
              margin: 10,
              flex: 1,
            }}
          >
            <Text>Item {item.name}</Text>
            <Text>Item {item.age}</Text>
            <View
              style={{
                backgroundColor: 'dodgerblue',
                //   height: 100,
                margin: 10,
                padding: 26,
                flexDirection: 'row',
              }}
            >
              <Text>Item {item.name}</Text>
              <Text>Item {item.age}</Text>
            </View>
          </View>
        )}
      /> */}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingTop: 80,
    flex: 1,
    // alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: Dimensions.get('window').width,
    flex: 1,
    marginVertical: 20,
  },
});
