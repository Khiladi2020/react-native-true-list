import { View, StyleSheet, Dimensions } from 'react-native';
import { TrueListView } from 'react-native-true-list';
import BASIC_TEMPLATE1 from './templates/basic1';

export default function App() {
  return (
    <View style={styles.container}>
      <TrueListView
        style={styles.box}
        items={BASIC_TEMPLATE1.data.map((item) => JSON.stringify(item))}
        dataFitter={JSON.stringify(BASIC_TEMPLATE1.dataFitter)}
        itemTemplate={JSON.stringify(BASIC_TEMPLATE1.template)}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: Dimensions.get('window').width,
    flex: 1,
    marginVertical: 20,
  },
});
