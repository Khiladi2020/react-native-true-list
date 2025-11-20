import { View, StyleSheet, Dimensions } from 'react-native';
import { TrueListView } from 'react-native-true-list';
import BASIC_TEMPLATE1 from './templates/basic1';

export default function App() {
  const TEMPLATE = BASIC_TEMPLATE1;

  return (
    <View style={styles.container}>
      <TrueListView
        style={styles.box}
        items={TEMPLATE.data}
        dataFitter={TEMPLATE.dataFitter}
        itemTemplate={TEMPLATE.template}
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
