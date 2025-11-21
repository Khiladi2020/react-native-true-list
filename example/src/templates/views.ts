const NAMES_POOL = ['Ravi', 'Sai', 'Rajesh', 'Raj', 'Rajeshwari', 'Bhavya'];
const DATA = new Array(1000).fill(0).map((_, index) => ({
  name: NAMES_POOL[index % NAMES_POOL.length],
  age: index,
}));

const BASIC_TEMPLATE1 = {
  data: DATA,
  template: {
    // This is the "root" view of your row (the LinearLayout)
    type: 'View',
    style: {
      padding: 56,
      backgroundColor: '#0055ff',
      marginVertical: 20,
      marginHorizontal: 10,
      justifyContent: 'center',
      // orientation: 'HORIZONTAL',
      orientation: 'HORIZONTAL',
    },
    children: [
      {
        type: 'View',
        style: {
          backgroundColor: '#ff55ff', // Use hex strings
          orientation: 'HORIZONTAL',
          height: 300,
          width: 300,
          // orientation: 'VERTICAL',
        },
      },
      {
        type: 'View',
        style: {
          backgroundColor: '#D2DCB6', // Use hex strings
          orientation: 'HORIZONTAL',
          marginHorizontal: 24,
          height: 300,
          width: 400,
          // orientation: 'VERTICAL',
        },
      },
    ],
  },
  dataFitter: {
    myTextView: {
      key: 'name',
      type: 'string',
    },
    myTextView2: {
      key: 'name',
      type: 'string',
    },
    myAgeTextView: {
      key: 'age',
      type: 'number',
    },
    myAgeTextView2: {
      key: 'age',
      type: 'number',
    },
  },
};

export default BASIC_TEMPLATE1;
